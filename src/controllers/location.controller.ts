import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { Location } from '@/models/models';

export const createLocationController = (
  service: CrudService<Location>,
): CrudController<Location> => new CrudController<Location>(service);
