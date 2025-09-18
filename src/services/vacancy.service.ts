import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { Vacancy } from '@/models/models';

export const createVacancyService = (dataSource: DataSource): CrudService<Vacancy> =>
  new CrudService<Vacancy>(dataSource.getRepository(Vacancy), 'Vacancy');
