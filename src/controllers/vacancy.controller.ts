import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { Vacancy } from '@/models/models';

export const createVacancyController = (
  service: CrudService<Vacancy>,
): CrudController<Vacancy> => new CrudController<Vacancy>(service);
