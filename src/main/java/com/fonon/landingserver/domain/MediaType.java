package com.fonon.landingserver.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;
import java.util.Set;

public enum MediaType {
    IMAGE("image"),
    VIDEO("video");

    private final String value;
    private static final Set<String> IMAGE_EXTENSIONS = Set.of(
            "jpg", "jpeg", "png", "gif", "bmp", "webp", "svg", "tiff", "tif", "ico", "heic", "heif"
    );
    private static final Set<String> VIDEO_EXTENSIONS = Set.of(
            "mp4", "mov", "avi", "mkv", "webm", "flv", "wmv", "m4v", "mpeg", "mpg", "3gp", "ogv"
    );

    MediaType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MediaType fromValue(String value) {
        for (MediaType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown media type: " + value);
    }

    @Override
    public String toString() {
        return value;
    }

    public static MediaType detectFromUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL is required to determine media type");
        }

        String normalized = url.trim();
        String lowerUrl = normalized.toLowerCase(Locale.ROOT);

        // Allow well-known streaming endpoints that do not expose a file extension
        if (lowerUrl.contains("youtube.com/") || lowerUrl.contains("youtu.be/")) {
            return VIDEO;
        }

        int fragmentIndex = normalized.indexOf('#');
        if (fragmentIndex >= 0) {
            normalized = normalized.substring(0, fragmentIndex);
        }

        int queryIndex = normalized.indexOf('?');
        if (queryIndex >= 0) {
            normalized = normalized.substring(0, queryIndex);
        }

        int lastSlashIndex = normalized.lastIndexOf('/');
        String filename = lastSlashIndex >= 0 ? normalized.substring(lastSlashIndex + 1) : normalized;
        if (filename.isEmpty()) {
            throw new IllegalArgumentException("Unable to extract filename from URL: " + url);
        }

        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex < 0 || lastDotIndex == filename.length() - 1) {
            throw new IllegalArgumentException("Unable to determine media type from URL: " + url);
        }

        String extension = filename.substring(lastDotIndex + 1).toLowerCase(Locale.ROOT);

        if (IMAGE_EXTENSIONS.contains(extension)) {
            return IMAGE;
        }

        if (VIDEO_EXTENSIONS.contains(extension)) {
            return VIDEO;
        }

        throw new IllegalArgumentException("Unsupported media type for URL: " + url);
    }
}
