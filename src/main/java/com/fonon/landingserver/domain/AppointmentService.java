package com.fonon.landingserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointment_services")
public class AppointmentService implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    @JsonBackReference("appointment-services")
    private Appointment appointment;

    @Column(name = "title_uz", columnDefinition = "text", nullable = false)
    private String titleUz;

    @Column(name = "title_ru", columnDefinition = "text")
    private String titleRu;

    @Column(name = "title_en", columnDefinition = "text")
    private String titleEn;

    @Column(name = "description_uz", columnDefinition = "text")
    private String descriptionUz;

    @Column(name = "description_ru", columnDefinition = "text")
    private String descriptionRu;

    @Column(name = "description_en", columnDefinition = "text")
    private String descriptionEn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonBackReference("location-services")
    private Location location;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamptz", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "timestamptz")
    private OffsetDateTime updatedAt;

    @OneToOne(mappedBy = "service")
    @JsonManagedReference("service-client")
    private ClientDetails client;

    @JsonSetter("time")
    public void setTimeFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.time = null;
            return;
        }
        if (node.isTextual()) {
            this.time = LocalTime.parse(node.asText());
            return;
        }
        if (node.isObject()) {
            if (!node.hasNonNull("hour") || !node.hasNonNull("minute")) {
                throw new IllegalArgumentException("Time JSON must include hour and minute");
            }
            int hour = node.get("hour").asInt();
            int minute = node.get("minute").asInt();
            int second = node.has("second") ? node.get("second").asInt() : 0;
            int nano = node.has("nano") ? node.get("nano").asInt() : 0;
            this.time = LocalTime.of(hour, minute, second, nano);
            return;
        }
        throw new IllegalArgumentException("Unsupported time format");
    }
}
