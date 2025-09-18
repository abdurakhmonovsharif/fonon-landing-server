import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { Journal } from '@/models/models';

export const createJournalController = (
  service: CrudService<Journal>,
): CrudController<Journal> => new CrudController<Journal>(service);
