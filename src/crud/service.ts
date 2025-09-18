import { DeepPartial, FindOptionsWhere, Repository } from 'typeorm';

import { badRequest, notFound } from './errors';
import { CrudEntity } from './types';

export interface ICrudService<T extends CrudEntity> {
  findAll(): Promise<T[]>;
  findOne(id: number): Promise<T>;
  create(payload: DeepPartial<T>): Promise<T>;
  update(id: number, payload: DeepPartial<T>): Promise<T>;
  remove(id: number): Promise<void>;
}

export class CrudService<T extends CrudEntity> implements ICrudService<T> {
  constructor(
    private readonly repository: Repository<T>,
    private readonly resourceName: string,
    private readonly relations?: string[],
  ) {}

  async findAll(): Promise<T[]> {
    return this.repository.find({ relations: this.relations });
  }

  async findOne(id: number): Promise<T> {
    const entity = await this.repository.findOne({
      where: { id } as FindOptionsWhere<T>,
      relations: this.relations,
    });

    if (!entity) {
      throw notFound(this.resourceName, id);
    }

    return entity;
  }

  async create(payload: DeepPartial<T>): Promise<T> {
    if (!payload) {
      throw badRequest('Request body is required');
    }

    const entity = this.repository.create(payload);
    return this.repository.save(entity);
  }

  async update(id: number, payload: DeepPartial<T>): Promise<T> {
    const entity = await this.repository.preload({
      id,
      ...(payload as object),
    } as DeepPartial<T> & { id: number });

    if (!entity) {
      throw notFound(this.resourceName, id);
    }

    return this.repository.save(entity);
  }

  async remove(id: number): Promise<void> {
    const result = await this.repository.delete(id);

    if (!result.affected) {
      throw notFound(this.resourceName, id);
    }
  }
}
