package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.JobRequest;
import com.fonon.landingserver.repository.JobRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class JobRequestCrudService extends CrudService<JobRequest> {

    public JobRequestCrudService(JobRequestRepository repository) {
        super(repository, "JobRequest");
    }
}
