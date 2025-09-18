import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createJournalController } from '@/controllers/journal.controller';
import { createJournalService } from '@/services/journal.service';

export async function journalRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createJournalService(AppDataSource);
  const controller = createJournalController(service);

  app.get('/journals', controller.list);
  app.get('/journals/:id', controller.get);
  app.post('/journals', controller.create);
  app.put('/journals/:id', controller.update);
  app.delete('/journals/:id', controller.remove);
}
