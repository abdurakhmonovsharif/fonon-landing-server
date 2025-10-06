package com.fonon.landingserver.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StorageResourceConfig implements WebMvcConfigurer {

    private final Path storagePath;
    private final String publicPath;

    public StorageResourceConfig(@Value("${app.file-storage.base-path:storage}") String basePath,
                                 @Value("${app.file-storage.public-path:/storage}") String publicPath) {
        this.storagePath = Paths.get(basePath).toAbsolutePath().normalize();
        this.publicPath = normalizePublicPath(publicPath);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String handlerPattern = publicPath.endsWith("/**") ? publicPath : publicPath + "/**";
        String location = storagePath.toUri().toString();
        if (!location.endsWith("/")) {
            location = location + "/";
        }
        registry.addResourceHandler(handlerPattern)
                .addResourceLocations(location);
    }

    private String normalizePublicPath(String path) {
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
}
