package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Location;
import com.fonon.landingserver.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationCrudService extends CrudService<Location> {
    private final FileStorageService storageService;

    public LocationCrudService(LocationRepository repository, FileStorageService storageService) {
        super(repository, "Location");
        this.storageService = storageService;
    }

    @Override
    public void remove(Long id) {
        Location location = findOne(id);
        storageService.deleteAll(location.getImages());
        super.remove(id);
    }
}
