package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Appointment;
import com.fonon.landingserver.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentCrudService extends CrudService<Appointment> {

    public AppointmentCrudService(AppointmentRepository repository) {
        super(repository, "Appointment");
    }
}
