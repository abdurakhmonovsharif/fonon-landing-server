package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.Location;
import com.fonon.landingserver.service.LocationCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
public class LocationController extends BaseCrudController<Location> {

    public LocationController(LocationCrudService service) {
        super(service);
    }
}
