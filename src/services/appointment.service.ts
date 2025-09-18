import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { Appointment } from '@/models/models';

export const createAppointmentService = (dataSource: DataSource): CrudService<Appointment> =>
  new CrudService<Appointment>(dataSource.getRepository(Appointment), 'Appointment', ['services']);
