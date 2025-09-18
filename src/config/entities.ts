import {
  About,
  Appointment,
  AppointmentService,
  ClientDetails,
  Contact,
  Famous,
  Gallery,
  JobRequest,
  Journal,
  Location,
  NavItem,
  NavItemProduct,
  News,
  UserEmail,
  Vacancy,
} from '@/models/models';

import { CrudEntityConfig } from '@/crud/types';

export const crudEntities: CrudEntityConfig<any>[] = [
  { name: 'Location', basePath: '/locations', entity: Location, relations: ['services'] },
  { name: 'Appointment', basePath: '/appointments', entity: Appointment, relations: ['services'] },
  {
    name: 'AppointmentService',
    basePath: '/appointment-services',
    entity: AppointmentService,
    relations: ['appointment', 'location', 'client'],
  },
  { name: 'ClientDetails', basePath: '/client-details', entity: ClientDetails, relations: ['service'] },
  { name: 'Contact', basePath: '/contacts', entity: Contact },
  { name: 'NavItem', basePath: '/nav-items', entity: NavItem, relations: ['parent', 'children', 'products'] },
  {
    name: 'NavItemProduct',
    basePath: '/nav-item-products',
    entity: NavItemProduct,
    relations: ['navitem', 'parent', 'children'],
  },
  { name: 'Famous', basePath: '/famous', entity: Famous },
  { name: 'About', basePath: '/about-sections', entity: About },
  { name: 'JobRequest', basePath: '/job-requests', entity: JobRequest },
  { name: 'Vacancy', basePath: '/vacancies', entity: Vacancy },
  { name: 'Journal', basePath: '/journals', entity: Journal },
  { name: 'News', basePath: '/news', entity: News },
  { name: 'Gallery', basePath: '/gallery', entity: Gallery },
  { name: 'UserEmail', basePath: '/user-emails', entity: UserEmail },
];
