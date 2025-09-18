import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { NavItemProduct } from '@/models/models';

export const createNavItemProductService = (
  dataSource: DataSource,
): CrudService<NavItemProduct> =>
  new CrudService<NavItemProduct>(dataSource.getRepository(NavItemProduct), 'NavItemProduct', [
    'navitem',
    'parent',
    'children',
  ]);
