import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { NavItem } from '@/models/models';

export const createNavItemService = (dataSource: DataSource): CrudService<NavItem> =>
  new CrudService<NavItem>(dataSource.getRepository(NavItem), 'NavItem', [
    'parent',
    'children',
    'products',
  ]);
