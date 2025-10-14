package com.fonon.landingserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phonenumber", columnDefinition = "text")
    private String phoneNumber;

    @Column(columnDefinition = "text")
    private String email;

    @Column(columnDefinition = "text")
    private String address;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "contact_social_links", joinColumns = @JoinColumn(name = "contact_id"))
    private List<SocialLink> socialLinks = new ArrayList<>();

    public void setSocialLinks(List<SocialLink> socialLinks) {
        this.socialLinks = socialLinks == null ? new ArrayList<>() : new ArrayList<>(socialLinks);
    }

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamptz")
    private OffsetDateTime updatedAt;
}
