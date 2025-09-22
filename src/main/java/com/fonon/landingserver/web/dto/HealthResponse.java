package com.fonon.landingserver.web.dto;

import java.time.OffsetDateTime;

public record HealthResponse(String status, OffsetDateTime timestamp) {
}
