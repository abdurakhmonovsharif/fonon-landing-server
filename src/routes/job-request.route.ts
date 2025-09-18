import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createJobRequestController } from '@/controllers/job-request.controller';
import { createJobRequestService } from '@/services/job-request.service';

export async function jobRequestRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createJobRequestService(AppDataSource);
  const controller = createJobRequestController(service);

  app.get('/job-requests', controller.list);
  app.get('/job-requests/:id', controller.get);
  app.post('/job-requests', controller.create);
  app.put('/job-requests/:id', controller.update);
  app.delete('/job-requests/:id', controller.remove);
}
