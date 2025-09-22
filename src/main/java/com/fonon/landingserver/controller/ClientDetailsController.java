package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.ClientDetails;
import com.fonon.landingserver.service.ClientDetailsCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client-details")
public class ClientDetailsController extends BaseCrudController<ClientDetails> {

    public ClientDetailsController(ClientDetailsCrudService service) {
        super(service);
    }
}
