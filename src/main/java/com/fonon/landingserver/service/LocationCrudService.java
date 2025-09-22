package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Location;
import com.fonon.landingserver.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationCrudService extends CrudService<Location> {

    public LocationCrudService(LocationRepository repository) {
        super(repository, "Location");
    }
}
