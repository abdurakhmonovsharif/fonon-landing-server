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
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vacancy")
public class Vacancy implements Identifiable {

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

    @Column(columnDefinition = "text")
    private String location;

    @Column(name = "employment_type", columnDefinition = "text")
    private String employmentType;

    @Column(name = "salary_min", precision = 19, scale = 2)
    private BigDecimal salaryMin;

    @Column(name = "salary_max", precision = 19, scale = 2)
    private BigDecimal salaryMax;

    @Column
    private LocalDate deadline;

    @Column(name = "is_active")
    private Boolean active = Boolean.TRUE;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamptz")
    private OffsetDateTime updatedAt;
}
