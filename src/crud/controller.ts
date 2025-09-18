import { FastifyReply, FastifyRequest } from 'fastify';
import { DeepPartial } from 'typeorm';

import { badRequest, HttpError } from './errors';
import { ICrudService } from './service';
import { CrudEntity } from './types';

type IdParams = { id: string };

export interface ICrudController<T extends CrudEntity> {
  list(req: FastifyRequest, reply: FastifyReply): Promise<void>;
  get(req: FastifyRequest<{ Params: IdParams }>, reply: FastifyReply): Promise<void>;
  create(req: FastifyRequest<{ Body: DeepPartial<T> }>, reply: FastifyReply): Promise<void>;
  update(
    req: FastifyRequest<{ Params: IdParams; Body: DeepPartial<T> }>,
    reply: FastifyReply,
  ): Promise<void>;
  remove(req: FastifyRequest<{ Params: IdParams }>, reply: FastifyReply): Promise<void>;
}

export class CrudController<T extends CrudEntity> implements ICrudController<T> {
  constructor(private readonly service: ICrudService<T>) {}

  list = async (req: FastifyRequest, reply: FastifyReply): Promise<void> => {
    try {
      const data = await this.service.findAll();
      reply.send(data);
    } catch (error) {
      this.handleError(reply, error);
    }
  };

  get = async (
    req: FastifyRequest<{ Params: IdParams }>,
    reply: FastifyReply,
  ): Promise<void> => {
    try {
      const id = this.parseId(req.params);
      const data = await this.service.findOne(id);
      reply.send(data);
    } catch (error) {
      this.handleError(reply, error);
    }
  };

  create = async (
    req: FastifyRequest<{ Body: DeepPartial<T> }>,
    reply: FastifyReply,
  ): Promise<void> => {
    try {
      const body = req.body as DeepPartial<T> | undefined;

      if (!body) {
        throw badRequest('Request body is required');
      }

      const created = await this.service.create(body);
      reply.code(201).send(created);
    } catch (error) {
      this.handleError(reply, error);
    }
  };

  update = async (
    req: FastifyRequest<{ Params: IdParams; Body: DeepPartial<T> }>,
    reply: FastifyReply,
  ): Promise<void> => {
    try {
      const id = this.parseId(req.params);
      const body = req.body as DeepPartial<T> | undefined;

      if (!body) {
        throw badRequest('Request body is required');
      }

      const updated = await this.service.update(id, body);
      reply.send(updated);
    } catch (error) {
      this.handleError(reply, error);
    }
  };

  remove = async (
    req: FastifyRequest<{ Params: IdParams }>,
    reply: FastifyReply,
  ): Promise<void> => {
    try {
      const id = this.parseId(req.params);
      await this.service.remove(id);
      reply.code(204).send();
    } catch (error) {
      this.handleError(reply, error);
    }
  };

  private parseId(params: Partial<IdParams>): number {
    if (!params.id) {
      throw badRequest('Path parameter "id" is required');
    }

    const numericId = Number(params.id);

    if (Number.isNaN(numericId)) {
      throw badRequest(`Invalid id parameter: ${params.id}`);
    }

    return numericId;
  }

  private handleError(reply: FastifyReply, error: unknown): void {
    if (error instanceof HttpError) {
      reply.code(error.statusCode).send({ message: error.message });
      return;
    }

    reply.code(500).send({ message: 'Internal Server Error' });
  }
}
