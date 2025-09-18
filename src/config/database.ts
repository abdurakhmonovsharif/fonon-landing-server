import 'reflect-metadata';
import { DataSource } from 'typeorm';
import { About, Appointment, AppointmentService, ClientDetails, Contact, Famous, Gallery, JobRequest, Journal, Location, NavItem, NavItemProduct, News, UserEmail, Vacancy } from '../models/models';

export const AppDataSource = new DataSource({
  type: 'postgres',
  host: process.env.DB_HOST || 'localhost',
  port: +(process.env.DB_PORT || 5432),
  username: process.env.DB_USER || 'postgres',
  password: process.env.DB_PASS || 'root',
  database: process.env.DB_NAME || 'fonon_landing',
  synchronize: true,
  logging: false,
  entities: [Location, Appointment, AppointmentService, ClientDetails, Contact, NavItem, NavItemProduct, Famous, About, JobRequest, Vacancy, Journal, News, Gallery, UserEmail],
  migrations: ['src/migrations/*.js'],
  subscribers: [],
});

export const initializeDataSource = async (): Promise<DataSource> => {
  if (AppDataSource.isInitialized) {
    return AppDataSource;
  }

  return AppDataSource.initialize();
};
