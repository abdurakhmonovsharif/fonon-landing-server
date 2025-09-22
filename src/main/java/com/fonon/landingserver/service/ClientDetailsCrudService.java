package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.ClientDetails;
import com.fonon.landingserver.repository.ClientDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsCrudService extends CrudService<ClientDetails> {

    public ClientDetailsCrudService(ClientDetailsRepository repository) {
        super(repository, "ClientDetails");
    }
}
