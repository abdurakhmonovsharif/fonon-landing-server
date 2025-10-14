package com.fonon.landingserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class SocialLink {

    @Column(name = "platform", columnDefinition = "text", nullable = false)
    private String platform;

    @Column(name = "url", columnDefinition = "text", nullable = false)
    private String url;

    public SocialLink(String platform, String url) {
        setPlatform(platform);
        setUrl(url);
    }

    public void setPlatform(String platform) {
        if (platform == null || platform.trim().isEmpty()) {
            throw new IllegalArgumentException("Platform is required");
        }
        this.platform = platform.trim();
    }

    public void setUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("Social link URL is required");
        }
        this.url = url.trim();
    }
}
