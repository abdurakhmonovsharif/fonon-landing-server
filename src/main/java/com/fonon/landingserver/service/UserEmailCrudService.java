package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.UserEmail;
import com.fonon.landingserver.repository.UserEmailRepository;
import org.springframework.stereotype.Service;

@Service
public class UserEmailCrudService extends CrudService<UserEmail> {

    public UserEmailCrudService(UserEmailRepository repository) {
        super(repository, "UserEmail");
    }
}
