import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createFamousController } from '@/controllers/famous.controller';
import { createFamousService } from '@/services/famous.service';

export async function famousRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createFamousService(AppDataSource);
  const controller = createFamousController(service);

  app.get('/famous', controller.list);
  app.get('/famous/:id', controller.get);
  app.post('/famous', controller.create);
  app.put('/famous/:id', controller.update);
  app.delete('/famous/:id', controller.remove);
}
