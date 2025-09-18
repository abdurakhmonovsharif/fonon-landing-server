import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { ClientDetails } from '@/models/models';

export const createClientDetailsController = (
  service: CrudService<ClientDetails>,
): CrudController<ClientDetails> => new CrudController<ClientDetails>(service);
