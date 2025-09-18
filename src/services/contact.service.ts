import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { Contact } from '@/models/models';

export const createContactService = (dataSource: DataSource): CrudService<Contact> =>
  new CrudService<Contact>(dataSource.getRepository(Contact), 'Contact');
