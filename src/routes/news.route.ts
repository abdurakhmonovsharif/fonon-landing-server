import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createNewsController } from '@/controllers/news.controller';
import { createNewsService } from '@/services/news.service';

export async function newsRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createNewsService(AppDataSource);
  const controller = createNewsController(service);

  app.get('/news', controller.list);
  app.get('/news/:id', controller.get);
  app.post('/news', controller.create);
  app.put('/news/:id', controller.update);
  app.delete('/news/:id', controller.remove);
}
