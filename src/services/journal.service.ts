import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { Journal } from '@/models/models';

export const createJournalService = (dataSource: DataSource): CrudService<Journal> =>
  new CrudService<Journal>(dataSource.getRepository(Journal), 'Journal');
