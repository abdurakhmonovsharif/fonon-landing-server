import { EntityTarget } from 'typeorm';

export interface CrudEntity {
  id: number;
}

export interface CrudEntityConfig<T extends CrudEntity> {
  name: string;
  basePath: string;
  entity: EntityTarget<T>;
  relations?: string[];
}
