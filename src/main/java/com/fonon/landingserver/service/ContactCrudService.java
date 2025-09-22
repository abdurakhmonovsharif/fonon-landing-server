package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.Contact;
import com.fonon.landingserver.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactCrudService extends CrudService<Contact> {

    public ContactCrudService(ContactRepository repository) {
        super(repository, "Contact");
    }
}
