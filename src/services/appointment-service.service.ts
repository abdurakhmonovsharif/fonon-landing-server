import { DataSource } from 'typeorm';

import { CrudService } from '@/crud/service';
import { AppointmentService as AppointmentServiceEntity } from '@/models/models';

export const createAppointmentServiceService = (
  dataSource: DataSource,
): CrudService<AppointmentServiceEntity> =>
  new CrudService<AppointmentServiceEntity>(dataSource.getRepository(AppointmentServiceEntity), 'AppointmentService', [
    'appointment',
    'location',
    'client',
  ]);
