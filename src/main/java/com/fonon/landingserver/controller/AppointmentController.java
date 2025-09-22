package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.Appointment;
import com.fonon.landingserver.service.AppointmentCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController extends BaseCrudController<Appointment> {

    public AppointmentController(AppointmentCrudService service) {
        super(service);
    }
}
