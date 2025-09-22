package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.AppointmentService;
import com.fonon.landingserver.service.AppointmentServiceCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointment-services")
public class AppointmentServiceController extends BaseCrudController<AppointmentService> {

    public AppointmentServiceController(AppointmentServiceCrudService service) {
        super(service);
    }
}
