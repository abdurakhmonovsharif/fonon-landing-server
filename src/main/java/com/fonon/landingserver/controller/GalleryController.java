package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.Gallery;
import com.fonon.landingserver.service.GalleryCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController extends BaseCrudController<Gallery> {

    public GalleryController(GalleryCrudService service) {
        super(service);
    }
}
