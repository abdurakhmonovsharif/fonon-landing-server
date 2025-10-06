package com.fonon.landingserver.controller;

import com.fonon.landingserver.service.FileStorageService;
import com.fonon.landingserver.service.FileStorageService.StoredFile;
import com.fonon.landingserver.web.dto.FileUploadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@Tag(name = "Files")
public class FileController {

    private final FileStorageService storageService;

    public FileController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(value = "/{category}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a file", description = "Stores a file under the provided category and returns a public URL")
    public ResponseEntity<FileUploadResponse> upload(@PathVariable String category,
                                                     @RequestParam("file") MultipartFile file) {
        StoredFile storedFile = storageService.store(file, category);
        FileUploadResponse response = new FileUploadResponse(
                storedFile.filename(),
                storedFile.relativePath(),
                storedFile.url(),
                storedFile.size(),
                storedFile.extension()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Operation(summary = "Delete a stored file", description = "Removes a file from storage using the provided path or URL")
    public ResponseEntity<Map<String, Boolean>> delete(@RequestParam("path") String path) {
        storageService.delete(path);
        return ResponseEntity.ok(Map.of("deleted", true));
    }
}
