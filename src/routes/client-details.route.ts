import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createClientDetailsController } from '@/controllers/client-details.controller';
import { createClientDetailsService } from '@/services/client-details.service';

export async function clientDetailsRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createClientDetailsService(AppDataSource);
  const controller = createClientDetailsController(service);

  app.get('/client-details', controller.list);
  app.get('/client-details/:id', controller.get);
  app.post('/client-details', controller.create);
  app.put('/client-details/:id', controller.update);
  app.delete('/client-details/:id', controller.remove);
}
