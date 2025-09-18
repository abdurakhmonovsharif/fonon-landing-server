import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { Famous } from '@/models/models';

export const createFamousService = (dataSource: DataSource): CrudService<Famous> =>
  new CrudService<Famous>(dataSource.getRepository(Famous), 'Famous');
