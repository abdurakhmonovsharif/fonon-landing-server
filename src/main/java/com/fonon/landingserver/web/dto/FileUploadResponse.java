package com.fonon.landingserver.web.dto;

public record FileUploadResponse(
        String filename,
        String path,
        String url,
        long size,
        String extension
) {
}
