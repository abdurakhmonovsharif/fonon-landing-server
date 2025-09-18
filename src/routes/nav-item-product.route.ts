import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createNavItemProductController } from '@/controllers/nav-item-product.controller';
import { createNavItemProductService } from '@/services/nav-item-product.service';

export async function navItemProductRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createNavItemProductService(AppDataSource);
  const controller = createNavItemProductController(service);

  app.get('/nav-item-products', controller.list);
  app.get('/nav-item-products/:id', controller.get);
  app.post('/nav-item-products', controller.create);
  app.put('/nav-item-products/:id', controller.update);
  app.delete('/nav-item-products/:id', controller.remove);
}
