import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createAppointmentServiceController } from '@/controllers/appointment-service.controller';
import { createAppointmentServiceService } from '@/services/appointment-service.service';

export async function appointmentServiceRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createAppointmentServiceService(AppDataSource);
  const controller = createAppointmentServiceController(service);

  app.get('/appointment-services', controller.list);
  app.get('/appointment-services/:id', controller.get);
  app.post('/appointment-services', controller.create);
  app.put('/appointment-services/:id', controller.update);
  app.delete('/appointment-services/:id', controller.remove);
}
