package com.fonon.landingserver.service;

import com.fonon.landingserver.domain.JobRequest;
import com.fonon.landingserver.repository.JobRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class JobRequestCrudService extends CrudService<JobRequest> {

    private final FileStorageService storageService;

    public JobRequestCrudService(JobRequestRepository repository, FileStorageService storageService) {
        super(repository, "JobRequest");
        this.storageService = storageService;
    }

    @Override
    public void remove(Long id) {
        JobRequest jobRequest = findOne(id);
        storageService.delete(jobRequest.getFile());
        super.remove(id);
    }
}
