package com.fonon.landingserver.web.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record FamousDTO(
        String slug,
        String titleUz,
        String titleRu,
        String titleEn,
        String bodyUz,
        String bodyRu,
        String bodyEn,
        List<String> images,
        OffsetDateTime published_at,
        List<LastItemsDTO> lastItems
) {
}
