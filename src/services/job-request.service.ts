import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { JobRequest } from '@/models/models';

export const createJobRequestService = (dataSource: DataSource): CrudService<JobRequest> =>
  new CrudService<JobRequest>(dataSource.getRepository(JobRequest), 'JobRequest');
