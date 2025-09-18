import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createVacancyController } from '@/controllers/vacancy.controller';
import { createVacancyService } from '@/services/vacancy.service';

export async function vacancyRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createVacancyService(AppDataSource);
  const controller = createVacancyController(service);

  app.get('/vacancies', controller.list);
  app.get('/vacancies/:id', controller.get);
  app.post('/vacancies', controller.create);
  app.put('/vacancies/:id', controller.update);
  app.delete('/vacancies/:id', controller.remove);
}
