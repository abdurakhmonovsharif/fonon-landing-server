package com.fonon.landingserver.web.dto;

import java.time.OffsetDateTime;

public record LastItemsDTO(
        Long id,
        String slug,
        String titleUz,
        String titleRu,
        String titleEn,
        String image,
        OffsetDateTime published_at
) {
}
