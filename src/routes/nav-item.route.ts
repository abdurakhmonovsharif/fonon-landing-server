import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createNavItemController } from '@/controllers/nav-item.controller';
import { createNavItemService } from '@/services/nav-item.service';

export async function navItemRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createNavItemService(AppDataSource);
  const controller = createNavItemController(service);

  app.get('/nav-items', controller.list);
  app.get('/nav-items/:id', controller.get);
  app.post('/nav-items', controller.create);
  app.put('/nav-items/:id', controller.update);
  app.delete('/nav-items/:id', controller.remove);
}
