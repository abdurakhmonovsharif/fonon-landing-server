import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { Location } from '@/models/models';

export const createLocationService = (dataSource: DataSource): CrudService<Location> =>
  new CrudService<Location>(dataSource.getRepository(Location), 'Location', ['services']);
