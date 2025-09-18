import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { UserEmail } from '@/models/models';

export const createUserEmailService = (dataSource: DataSource): CrudService<UserEmail> =>
  new CrudService<UserEmail>(dataSource.getRepository(UserEmail), 'UserEmail');
