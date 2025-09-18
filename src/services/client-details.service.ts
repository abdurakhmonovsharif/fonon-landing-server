import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { ClientDetails } from '@/models/models';

export const createClientDetailsService = (dataSource: DataSource): CrudService<ClientDetails> =>
  new CrudService<ClientDetails>(dataSource.getRepository(ClientDetails), 'ClientDetails', ['service']);
