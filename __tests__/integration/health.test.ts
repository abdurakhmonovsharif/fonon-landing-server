import { FastifyInstance } from 'fastify';
import { build } from '@/app';
import supertest from 'supertest';

describe('Health Endpoint', () => {
  let app: FastifyInstance;

  beforeAll(async () => {
    app = await build();
    await app.ready();
  }, 10000);

  afterAll(async () => {
    await app.close();
  }, 10000);

  it('should return 200 and health check data', async () => {
    const response = await supertest(app.server)
      .get('/health')
      .expect('Content-Type', /json/)
      .expect(200);

    expect(response.body).toHaveProperty('status', 'ok');
    expect(response.body).toHaveProperty('timestamp');
    expect(new Date(response.body.timestamp)).toBeInstanceOf(Date);
  }, 10000);
});
