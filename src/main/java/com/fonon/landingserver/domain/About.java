package com.fonon.landingserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.SqlTypes;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "about")
public class About implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_uz", columnDefinition = "text", nullable = false)
    private String titleUz;

    @Column(name = "title_ru", columnDefinition = "text")
    private String titleRu;

    @Column(name = "title_en", columnDefinition = "text")
    private String titleEn;

    @Column(name = "body_uz", columnDefinition = "text")
    private String bodyUz;

    @Column(name = "body_ru", columnDefinition = "text")
    private String bodyRu;

    @Column(name = "body_en", columnDefinition = "text")
    private String bodyEn;

    @Setter(AccessLevel.NONE)
    @NotBlank
    @Column(name = "slug", columnDefinition = "text", nullable = false, unique = true)
    private String slug;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "text[]")
    private List<String> images = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamptz")
    private OffsetDateTime updatedAt;

    public void setSlug(String slug) {
        if (slug == null) {
            throw new IllegalArgumentException("Slug is required");
        }
        String sanitized = slug.trim();
        if (sanitized.isEmpty()) {
            throw new IllegalArgumentException("Slug must not be blank");
        }
        this.slug = sanitized;
    }
}
