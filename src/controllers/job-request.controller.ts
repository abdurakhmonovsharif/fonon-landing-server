import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { JobRequest } from '@/models/models';

export const createJobRequestController = (
  service: CrudService<JobRequest>,
): CrudController<JobRequest> => new CrudController<JobRequest>(service);
