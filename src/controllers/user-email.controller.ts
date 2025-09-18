import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { UserEmail } from '@/models/models';

export const createUserEmailController = (
  service: CrudService<UserEmail>,
): CrudController<UserEmail> => new CrudController<UserEmail>(service);
