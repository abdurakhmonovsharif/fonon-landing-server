import { FastifyInstance } from 'fastify';

import { buildOpenApiSpec } from '@/docs/openapi';
import { initializeDataSource } from '@/config/database';

export const registerScalarDocs = async (app: FastifyInstance): Promise<void> => {
  try {
    const scalarModule = await import('@scalar/fastify-api-reference');
    console.log('scalarModule:', scalarModule);
    
    const scalarPlugin = scalarModule.default ?? scalarModule;

    await initializeDataSource();

    const spec = buildOpenApiSpec();

    await app.register(scalarPlugin, {
      routePrefix: '/docs',
      configuration: {
        content: spec,
      },
    });
  } catch (error) {
    if (error && typeof error === 'object' && 'code' in error && error.code === 'ERR_MODULE_NOT_FOUND') {
      console.warn(
        'Scalar documentation disabled. Install optional dependency @scalar/fastify-api-reference to enable /docs.',
      );
      return;
    }

    console.warn('Scalar documentation plugin failed to register:', error);
  }
};
