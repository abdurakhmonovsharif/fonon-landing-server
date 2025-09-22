package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.AppointmentService;
import com.fonon.landingserver.repository.AppointmentServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceCrudService extends CrudService<AppointmentService> {

    public AppointmentServiceCrudService(AppointmentServiceRepository repository) {
        super(repository, "AppointmentService");
    }
}
