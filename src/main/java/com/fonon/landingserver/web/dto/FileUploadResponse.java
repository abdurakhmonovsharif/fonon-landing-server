package com.fonon.landingserver.web.dto;

public record FileUploadResponse(
        Long id,
        String filename,
        String path,
        String url,
        long size,
        String extension
) {
}
