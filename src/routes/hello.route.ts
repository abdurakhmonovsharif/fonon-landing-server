import { FastifyInstance } from 'fastify';
import { HelloController } from '../controllers/hello.controller';
import { HelloResponse } from '../interfaces/hello.interface';

export async function helloRoutes(fastify: FastifyInstance): Promise<void> {
  const helloController = new HelloController();

  fastify.get<{ Reply: HelloResponse }>('/', async () => helloController.getHello());
}