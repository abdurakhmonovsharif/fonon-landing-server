import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createContactController } from '@/controllers/contact.controller';
import { createContactService } from '@/services/contact.service';

export async function contactRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createContactService(AppDataSource);
  const controller = createContactController(service);

  app.get('/contacts', controller.list);
  app.get('/contacts/:id', controller.get);
  app.post('/contacts', controller.create);
  app.put('/contacts/:id', controller.update);
  app.delete('/contacts/:id', controller.remove);
}
