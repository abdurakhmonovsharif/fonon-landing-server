import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { About } from '@/models/models';

export const createAboutController = (
  service: CrudService<About>,
): CrudController<About> => new CrudController<About>(service);
