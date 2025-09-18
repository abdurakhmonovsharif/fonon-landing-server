import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { News } from '@/models/models';

export const createNewsService = (dataSource: DataSource): CrudService<News> =>
  new CrudService<News>(dataSource.getRepository(News), 'News');
