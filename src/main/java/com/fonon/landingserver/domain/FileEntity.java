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

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stored_file")
public class FileEntity implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "relative_path", columnDefinition = "text", nullable = false, unique = true)
    private String relativePath;

    @Column(name = "url", columnDefinition = "text", nullable = false)
    private String url;

    @Column(name = "size_bytes", nullable = false)
    private long size;

    @Column(name = "extension")
    private String extension;

    @Column(name = "original_filename", columnDefinition = "text")
    private String originalFilename;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private OffsetDateTime createdAt;
}
