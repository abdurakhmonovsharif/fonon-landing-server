package com.fonon.landingserver.repository;

import com.fonon.landingserver.domain.AppointmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentServiceRepository extends JpaRepository<AppointmentService, Long> {
}
