import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { News } from '@/models/models';

export const createNewsController = (
  service: CrudService<News>,
): CrudController<News> => new CrudController<News>(service);
