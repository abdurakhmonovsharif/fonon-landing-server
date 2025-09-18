import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { AppointmentService as AppointmentServiceEntity } from '@/models/models';

export const createAppointmentServiceController = (
  service: CrudService<AppointmentServiceEntity>,
): CrudController<AppointmentServiceEntity> =>
  new CrudController<AppointmentServiceEntity>(service);
