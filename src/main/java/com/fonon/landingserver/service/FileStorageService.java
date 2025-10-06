package com.fonon.landingserver.service;

import com.fonon.landingserver.exception.BadRequestException;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);
    private static final Set<String> PDF_ONLY_CATEGORIES = Set.of("jobrequest", "job-request", "job_request");

    private final Path rootDirectory;
    private final String publicPrefix;

    public FileStorageService(@Value("${app.file-storage.base-path:storage}") String basePath,
                              @Value("${app.file-storage.public-path:/storage}") String publicPath) {
        this.rootDirectory = Paths.get(basePath).toAbsolutePath().normalize();
        this.publicPrefix = ensureLeadingSlash(publicPath);
    }

    private String ensureLeadingSlash(String path) {
        if (path == null || path.isBlank()) {
            return "/storage";
        }
        String trimmed = path.trim();
        if (!trimmed.startsWith("/")) {
            trimmed = "/" + trimmed;
        }
        if (trimmed.endsWith("/")) {
            trimmed = trimmed.substring(0, trimmed.length() - 1);
        }
        return trimmed;
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootDirectory);
        } catch (IOException e) {
            throw new IllegalStateException("Could not create storage directory: " + rootDirectory, e);
        }
    }

    public StoredFile store(MultipartFile file, String category) {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("File is required");
        }
        String normalizedCategory = normalizeCategory(category);
        boolean pdfOnly = PDF_ONLY_CATEGORIES.contains(normalizedCategory);
        String originalExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String extension = (originalExtension == null || originalExtension.isBlank())
                ? inferExtension(file.getContentType())
                : originalExtension;
        if (extension != null) {
            extension = extension.toLowerCase(Locale.ROOT);
        }

        if (pdfOnly && (extension == null || !extension.equals("pdf"))) {
            throw new BadRequestException("Job request files must be in PDF format");
        }

        Path categoryDir = rootDirectory.resolve(normalizedCategory).normalize();
        createDirectories(categoryDir);

        String filename = UUID.randomUUID().toString().replace("-", "");
        if (extension != null && !extension.isBlank()) {
            filename = filename + "." + extension;
        }

        Path target = categoryDir.resolve(filename).normalize();
        if (!target.startsWith(rootDirectory)) {
            throw new BadRequestException("Invalid storage path");
        }

        try {
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to store file", e);
        }

        String relativePath = normalizedCategory + "/" + filename;
        String publicUrl = buildPublicUrl(relativePath);
        return new StoredFile(filename, relativePath, publicUrl, file.getSize(), extension);
    }

    public void delete(String storedPath) {
        resolvePath(storedPath).ifPresent(path -> {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                log.warn("Failed to delete file {}", path, e);
            }
        });
    }

    public void deleteAll(Collection<String> storedPaths) {
        if (storedPaths == null) {
            return;
        }
        // Use a set to avoid deleting the same file multiple times
        Set<String> unique = new HashSet<>();
        for (String value : storedPaths) {
            if (value != null && !value.isBlank()) {
                unique.add(value);
            }
        }
        unique.forEach(this::delete);
    }

    private java.util.Optional<Path> resolvePath(String storedPath) {
        if (storedPath == null || storedPath.isBlank()) {
            return java.util.Optional.empty();
        }
        String sanitized = storedPath.trim();
        if (sanitized.startsWith(publicPrefix)) {
            sanitized = sanitized.substring(publicPrefix.length());
        }
        if (sanitized.startsWith("/")) {
            sanitized = sanitized.substring(1);
        }
        if (sanitized.contains("..")) {
            return java.util.Optional.empty();
        }
        Path path = rootDirectory.resolve(sanitized).normalize();
        if (!path.startsWith(rootDirectory)) {
            return java.util.Optional.empty();
        }
        return java.util.Optional.of(path);
    }

    private String buildPublicUrl(String relativePath) {
        if (relativePath == null || relativePath.isBlank()) {
            return publicPrefix;
        }
        return publicPrefix + "/" + relativePath.replace("\\", "/");
    }

    private String normalizeCategory(String rawCategory) {
        if (rawCategory == null || rawCategory.isBlank()) {
            throw new BadRequestException("Category is required");
        }
        String lower = rawCategory.trim().toLowerCase(Locale.ROOT);
        lower = lower.replaceAll("[\\s_]+", "-");
        lower = lower.replaceAll("[^a-z0-9-]", "");
        lower = lower.replaceAll("-+", "-");
        if (lower.isBlank()) {
            throw new BadRequestException("Invalid category");
        }
        return lower;
    }

    private String inferExtension(String contentType) {
        if (contentType == null) {
            return null;
        }
        return switch (contentType.toLowerCase(Locale.ROOT)) {
            case "image/jpeg", "image/jpg" -> "jpg";
            case "image/png" -> "png";
            case "image/gif" -> "gif";
            case "image/webp" -> "webp";
            case "application/pdf" -> "pdf";
            default -> null;
        };
    }

    private void createDirectories(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new IllegalStateException("Could not create directory: " + path, e);
        }
    }

    public Path getRootDirectory() {
        return rootDirectory;
    }

    public String getPublicPrefix() {
        return publicPrefix;
    }

    public record StoredFile(String filename, String relativePath, String url, long size, String extension) {}
}
