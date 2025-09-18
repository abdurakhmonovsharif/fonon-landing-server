import fastify, { FastifyInstance } from 'fastify';

import { registerScalarDocs } from './plugins/scalar';
import { healthRoutes } from './routes/health.route';
import { helloRoutes } from './routes/hello.route';
import { aboutRoutes } from './routes/about.route';
import { appointmentRoutes } from './routes/appointment.route';
import { appointmentServiceRoutes } from './routes/appointment-service.route';
import { clientDetailsRoutes } from './routes/client-details.route';
import { contactRoutes } from './routes/contact.route';
import { famousRoutes } from './routes/famous.route';
import { galleryRoutes } from './routes/gallery.route';
import { jobRequestRoutes } from './routes/job-request.route';
import { journalRoutes } from './routes/journal.route';
import { locationRoutes } from './routes/location.route';
import { navItemRoutes } from './routes/nav-item.route';
import { navItemProductRoutes } from './routes/nav-item-product.route';
import { newsRoutes } from './routes/news.route';
import { userEmailRoutes } from './routes/user-email.route';
import { vacancyRoutes } from './routes/vacancy.route';

export async function build(): Promise<FastifyInstance> {
  const app: FastifyInstance = fastify();

  await registerScalarDocs(app);

  // Register routes
  await app.register(healthRoutes);
  await app.register(helloRoutes);
  await app.register(locationRoutes, { prefix: '/api' });
  await app.register(appointmentRoutes, { prefix: '/api' });
  await app.register(appointmentServiceRoutes, { prefix: '/api' });
  await app.register(clientDetailsRoutes, { prefix: '/api' });
  await app.register(contactRoutes, { prefix: '/api' });
  await app.register(navItemRoutes, { prefix: '/api' });
  await app.register(navItemProductRoutes, { prefix: '/api' });
  await app.register(famousRoutes, { prefix: '/api' });
  await app.register(aboutRoutes, { prefix: '/api' });
  await app.register(jobRequestRoutes, { prefix: '/api' });
  await app.register(vacancyRoutes, { prefix: '/api' });
  await app.register(journalRoutes, { prefix: '/api' });
  await app.register(newsRoutes, { prefix: '/api' });
  await app.register(galleryRoutes, { prefix: '/api' });
  await app.register(userEmailRoutes, { prefix: '/api' });
   
  return app;
}

export default build;
