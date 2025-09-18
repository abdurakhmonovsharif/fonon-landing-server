import { FastifyInstance } from 'fastify';

import { CrudController } from './controller';
import { CrudService } from './service';
import { CrudEntity, CrudEntityConfig } from './types';
import { AppDataSource } from '@/config/database';

export async function registerCrudRoutes<T extends CrudEntity>(
  app: FastifyInstance,
  config: CrudEntityConfig<T>,
): Promise<void> {
  const repository = AppDataSource.getRepository<T>(config.entity);
  const service = new CrudService<T>(repository, config.name, config.relations);
  const controller = new CrudController<T>(service);

  app.get(config.basePath, controller.list);
  app.get(`${config.basePath}/:id`, controller.get);
  app.post(config.basePath, controller.create);
  app.put(`${config.basePath}/:id`, controller.update);
  app.delete(`${config.basePath}/:id`, controller.remove);
}
