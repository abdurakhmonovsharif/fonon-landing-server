import { CrudController } from '@/crud/controller';
import { CrudService } from '@/crud/service';
import { Appointment } from '@/models/models';

export const createAppointmentController = (
  service: CrudService<Appointment>,
): CrudController<Appointment> => new CrudController<Appointment>(service);
