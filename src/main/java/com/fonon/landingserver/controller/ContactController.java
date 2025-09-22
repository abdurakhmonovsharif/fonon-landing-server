package com.fonon.landingserver.controller;

import com.fonon.landingserver.domain.Contact;
import com.fonon.landingserver.service.ContactCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactController extends BaseCrudController<Contact> {

    public ContactController(ContactCrudService service) {
        super(service);
    }
}
