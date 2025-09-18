import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createLocationController } from '@/controllers/location.controller';
import { createLocationService } from '@/services/location.service';

export async function locationRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createLocationService(AppDataSource);
  const controller = createLocationController(service);

  app.get('/locations', controller.list);
  app.get('/locations/:id', controller.get);
  app.post('/locations', controller.create);
  app.put('/locations/:id', controller.update);
  app.delete('/locations/:id', controller.remove);
}
