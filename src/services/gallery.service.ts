import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { Gallery } from '@/models/models';

export const createGalleryService = (dataSource: DataSource): CrudService<Gallery> =>
  new CrudService<Gallery>(dataSource.getRepository(Gallery), 'Gallery');
