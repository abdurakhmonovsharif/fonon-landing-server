import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { About } from '@/models/models';

export const createAboutService = (dataSource: DataSource): CrudService<About> =>
  new CrudService<About>(dataSource.getRepository(About), 'About');
