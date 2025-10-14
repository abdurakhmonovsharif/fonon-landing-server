package com.fonon.landingserver.domain.converter;

import com.fonon.landingserver.domain.MediaType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MediaTypeAttributeConverter implements AttributeConverter<MediaType, String> {

    @Override
    public String convertToDatabaseColumn(MediaType attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public MediaType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : MediaType.fromValue(dbData);
    }
}
