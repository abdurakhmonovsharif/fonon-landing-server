import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { Gallery } from '@/models/models';

export const createGalleryController = (
  service: CrudService<Gallery>,
): CrudController<Gallery> => new CrudController<Gallery>(service);
