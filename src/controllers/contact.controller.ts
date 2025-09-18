import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { Contact } from '@/models/models';

export const createContactController = (
  service: CrudService<Contact>,
): CrudController<Contact> => new CrudController<Contact>(service);
