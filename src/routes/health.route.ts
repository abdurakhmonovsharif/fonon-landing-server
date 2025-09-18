import { FastifyInstance } from 'fastify';
import { HealthController } from '../controllers/health.controller';
import { HealthResponse } from '../interfaces/health.interface';

export async function healthRoutes(fastify: FastifyInstance): Promise<void> {
  const healthController = new HealthController();

  fastify.get<{ Reply: HealthResponse }>('/health', async () => healthController.getHealth());
}
