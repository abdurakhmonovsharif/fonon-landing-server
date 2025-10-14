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
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "gallery")
public class Gallery implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(name = "media_type", nullable = false)
    private MediaType mediaType;

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String url;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private OffsetDateTime createdAt;

    public void setUrl(String url) {
        String sanitizedUrl = url == null ? null : url.trim();
        MediaType detectedType = MediaType.detectFromUrl(sanitizedUrl);
        if (this.mediaType != null && this.mediaType != detectedType) {
            throw new IllegalArgumentException("URL media type does not match declared mediaType");
        }
        this.url = sanitizedUrl;
        this.mediaType = detectedType;
    }

    public void setMediaType(MediaType mediaType) {
        if (mediaType == null) {
            this.mediaType = null;
            return;
        }
        if (this.url != null) {
            MediaType detectedType = MediaType.detectFromUrl(this.url);
            if (detectedType != mediaType) {
                throw new IllegalArgumentException("Provided mediaType does not match URL extension");
            }
        }
        this.mediaType = mediaType;
    }
}
