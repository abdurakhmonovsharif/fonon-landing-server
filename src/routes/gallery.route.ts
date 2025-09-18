import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createGalleryController } from '@/controllers/gallery.controller';
import { createGalleryService } from '@/services/gallery.service';

export async function galleryRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createGalleryService(AppDataSource);
  const controller = createGalleryController(service);

  app.get('/gallery', controller.list);
  app.get('/gallery/:id', controller.get);
  app.post('/gallery', controller.create);
  app.put('/gallery/:id', controller.update);
  app.delete('/gallery/:id', controller.remove);
}
