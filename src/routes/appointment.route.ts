import { FastifyInstance } from 'fastify';

import { AppDataSource, initializeDataSource } from '@/config/database';
import { createAppointmentController } from '@/controllers/appointment.controller';
import { createAppointmentService } from '@/services/appointment.service';

export async function appointmentRoutes(app: FastifyInstance): Promise<void> {
  await initializeDataSource();

  const service = createAppointmentService(AppDataSource);
  const controller = createAppointmentController(service);

  app.get('/appointments', controller.list);
  app.get('/appointments/:id', controller.get);
  app.post('/appointments', controller.create);
  app.put('/appointments/:id', controller.update);
  app.delete('/appointments/:id', controller.remove);
}
