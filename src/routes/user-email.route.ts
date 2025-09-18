import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createUserEmailController } from '@/controllers/user-email.controller';
import { createUserEmailService } from '@/services/user-email.service';

export async function userEmailRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createUserEmailService(AppDataSource);
  const controller = createUserEmailController(service);

  app.get('/user-emails', controller.list);
  app.get('/user-emails/:id', controller.get);
  app.post('/user-emails', controller.create);
  app.put('/user-emails/:id', controller.update);
  app.delete('/user-emails/:id', controller.remove);
}
