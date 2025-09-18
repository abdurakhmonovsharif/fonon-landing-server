import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { Famous } from '@/models/models';

export const createFamousController = (
  service: CrudService<Famous>,
): CrudController<Famous> => new CrudController<Famous>(service);
