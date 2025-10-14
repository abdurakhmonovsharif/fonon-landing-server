package com.fonon.landingserver.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " with id " + id + " was not found");
    }

    public ResourceNotFoundException(String resource, String slug) {
        super(resource + " with slug '" + slug + "' was not found");
    }
}
