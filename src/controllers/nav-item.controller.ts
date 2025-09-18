import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { NavItem } from '@/models/models';

export const createNavItemController = (
  service: CrudService<NavItem>,
): CrudController<NavItem> => new CrudController<NavItem>(service);
