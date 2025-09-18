import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createAboutController } from '@/controllers/about.controller';
import { createAboutService } from '@/services/about.service';

export async function aboutRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createAboutService(AppDataSource);
  const controller = createAboutController(service);

  app.get('/about-sections', controller.list);
  app.get('/about-sections/:id', controller.get);
  app.post('/about-sections', controller.create);
  app.put('/about-sections/:id', controller.update);
  app.delete('/about-sections/:id', controller.remove);
}
