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
@Table(name = "job_requests")
public class JobRequest implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", columnDefinition = "text", nullable = false)
    private String firstName;

    @Column(name = "lastname", columnDefinition = "text")
    private String lastName;

    @Column(name = "phonenumber", columnDefinition = "text")
    private String phoneNumber;

    @Column(columnDefinition = "text")
    private String email;

    @Column(columnDefinition = "text")
    private String file;

    @Column(columnDefinition = "text")
    private String position;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private OffsetDateTime createdAt;
}
