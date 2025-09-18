import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { NavItemProduct } from '@/models/models';

export const createNavItemProductController = (
  service: CrudService<NavItemProduct>,
): CrudController<NavItemProduct> => new CrudController<NavItemProduct>(service);
