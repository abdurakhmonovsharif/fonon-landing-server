package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Gallery;
import com.fonon.landingserver.repository.GalleryRepository;
import org.springframework.stereotype.Service;

@Service
public class GalleryCrudService extends CrudService<Gallery> {

    public GalleryCrudService(GalleryRepository repository) {
        super(repository, "Gallery");
    }
}
