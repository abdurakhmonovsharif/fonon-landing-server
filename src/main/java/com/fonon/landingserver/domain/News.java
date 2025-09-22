package com.fonon.landingserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News implements Identifiable {

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

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "text[]")
    private List<String> images = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamptz")
    private OffsetDateTime updatedAt;

    @Column(name = "published_at", columnDefinition = "timestamptz")
    private OffsetDateTime publishedAt;
}
