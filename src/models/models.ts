import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
  ManyToOne,
  OneToMany,
  OneToOne,
  JoinColumn,
} from 'typeorm';

/**
 * 1. Location
 */
@Entity({ name: 'locations' })
export class Location {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text' })
  name_uz: string;

  @Column({ type: 'text', nullable: true })
  name_ru: string;

  @Column({ type: 'text', nullable: true })
  name_en: string;

  @Column({ type: 'text', nullable: true })
  map_tag: string;

  @Column({ type: 'time', nullable: true })
  open_time: string;

  @Column({ type: 'time', nullable: true })
  close_time: string;

  @Column('text', { array: true, default: '{}' })
  work_days: string[];

  @Column({ type: 'text', nullable: true })
  address: string;

  @Column('text', { array: true, default: '{}' })
  images: string[];

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamptz' })
  updated_at: Date;

  @OneToMany(() => AppointmentService, (s) => s.location)
  services: AppointmentService[];
}

/**
 * 2. Appointment
 */
@Entity({ name: 'appointments' })
export class Appointment {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'enum', enum: ['personality', 'store'] })
  type: 'personality' | 'store';

  @Column({ type: 'text', nullable: true })
  title_uz: string;

  @Column({ type: 'text', nullable: true })
  title_ru: string;

  @Column({ type: 'text', nullable: true })
  title_en: string;

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamptz' })
  updated_at: Date;

  @OneToMany(() => AppointmentService, (s) => s.appointment)
  services: AppointmentService[];
}

/**
 * 2.1 AppointmentService
 */
@Entity({ name: 'appointment_services' })
export class AppointmentService {
  @PrimaryGeneratedColumn()
  id: number;

  @ManyToOne(() => Appointment, (a) => a.services, { onDelete: 'CASCADE' })
  @JoinColumn({ name: 'appointment_id' })
  appointment: Appointment;

  @Column({ type: 'text' })
  title_uz: string;

  @Column({ type: 'text', nullable: true })
  title_ru: string;

  @Column({ type: 'text', nullable: true })
  title_en: string;

  @Column({ type: 'text', nullable: true })
  description_uz: string;

  @Column({ type: 'text', nullable: true })
  description_ru: string;

  @Column({ type: 'text', nullable: true })
  description_en: string;

  @ManyToOne(() => Location, (l) => l.services, { onDelete: 'SET NULL' })
  @JoinColumn({ name: 'location_id' })
  location: Location;

  @Column({ type: 'date', nullable: true })
  date: string;

  @Column({ type: 'time', nullable: true })
  time: string;

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamptz' })
  updated_at: Date;

  @OneToOne(() => ClientDetails, (c) => c.service)
  client: ClientDetails;
}

/**
 * 2.1.1 ClientDetails
 */
@Entity({ name: 'client_details' })
export class ClientDetails {
  @PrimaryGeneratedColumn()
  id: number;

  @OneToOne(() => AppointmentService, (s) => s.client, { onDelete: 'CASCADE' })
  @JoinColumn({ name: 'service_id' })
  service: AppointmentService;

  @Column({ type: 'text' })
  firstname: string;

  @Column({ type: 'text', nullable: true })
  lastname: string;

  @Column({ type: 'text', nullable: true })
  email: string;

  @Column({ type: 'text', nullable: true })
  phonenumber: string;

  @Column({ type: 'text', nullable: true })
  address: string;

  @Column({ type: 'text', nullable: true })
  comment: string;

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;
}

/**
 * 3. Contact
 */
@Entity({ name: 'contact' })
export class Contact {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text', nullable: true })
  phonenumber: string;

  @Column({ type: 'text', nullable: true })
  email: string;

  @Column({ type: 'text', nullable: true })
  address: string;

  @Column('text', { array: true, default: '{}' })
  socialmedia: string[];

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamptz' })
  updated_at: Date;
}

/**
 * 4. NavItem
 */
@Entity({ name: 'navitems' })
export class NavItem {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text' })
  name_uz: string;

  @Column({ type: 'text', nullable: true })
  name_ru: string;

  @Column({ type: 'text', nullable: true })
  name_en: string;

  @Column({ type: 'text', unique: true })
  slug: string;

  @ManyToOne(() => NavItem, (n) => n.children, { onDelete: 'SET NULL' })
  @JoinColumn({ name: 'parent_id' })
  parent: NavItem;

  @OneToMany(() => NavItem, (n) => n.parent)
  children: NavItem[];

  @OneToMany(() => NavItemProduct, (p) => p.navitem)
  products: NavItemProduct[];

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamptz' })
  updated_at: Date;
}

/**
 * 4.1 NavItemProduct
 */
@Entity({ name: 'nav_item_products' })
export class NavItemProduct {
  @PrimaryGeneratedColumn()
  id: number;

  @ManyToOne(() => NavItem, (n) => n.products, { onDelete: 'CASCADE' })
  @JoinColumn({ name: 'navitem_id' })
  navitem: NavItem;

  @Column({ type: 'text', nullable: true })
  img: string;

  @Column({ type: 'text', nullable: true })
  link: string;

  @ManyToOne(() => NavItemProduct, (p) => p.children, { onDelete: 'SET NULL' })
  @JoinColumn({ name: 'parent_id' })
  parent: NavItemProduct;

  @OneToMany(() => NavItemProduct, (p) => p.parent)
  children: NavItemProduct[];

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;
}

/**
 * 5. Famous
 */
@Entity({ name: 'famous' })
export class Famous {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text' })
  title_uz: string;

  @Column({ type: 'text', nullable: true })
  title_ru: string;

  @Column({ type: 'text', nullable: true })
  title_en: string;

  @Column({ type: 'text', nullable: true })
  body_uz: string;

  @Column({ type: 'text', nullable: true })
  body_ru: string;

  @Column({ type: 'text', nullable: true })
  body_en: string;

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamptz' })
  updated_at: Date;
}

/**
 * 6. About
 */
@Entity({ name: 'about' })
export class About {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text' })
  title_uz: string;

  @Column({ type: 'text', nullable: true })
  title_ru: string;

  @Column({ type: 'text', nullable: true })
  title_en: string;

  @Column({ type: 'text', nullable: true })
  body_uz: string;

  @Column({ type: 'text', nullable: true })
  body_ru: string;

  @Column({ type: 'text', nullable: true })
  body_en: string;

  @Column('text', { array: true, default: '{}' })
  images: string[];

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamptz' })
  updated_at: Date;
}

/**
 * 7. JobRequest
 */
@Entity({ name: 'job_requests' })
export class JobRequest {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text' })
  firstname: string;

  @Column({ type: 'text', nullable: true })
  lastname: string;

  @Column({ type: 'text', nullable: true })
  phonenumber: string;

  @Column({ type: 'text', nullable: true })
  email: string;

  @Column({ type: 'text', nullable: true })
  file: string;

  @Column({ type: 'text', nullable: true })
  position: string;

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;
}

/**
 * 8. Vacancy
 */
@Entity({ name: 'vacancy' })
export class Vacancy {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text' })
  title_uz: string;

  @Column({ type: 'text', nullable: true })
  title_ru: string;

  @Column({ type: 'text', nullable: true })
  title_en: string;

  @Column({ type: 'text', nullable: true })
  body_uz: string;

  @Column({ type: 'text', nullable: true })
  body_ru: string;

  @Column({ type: 'text', nullable: true })
  body_en: string;

  @Column({ type: 'text', nullable: true })
  location: string;

  @Column({ type: 'text', nullable: true })
  employment_type: string;

  @Column({ type: 'numeric', nullable: true })
  salary_min: number;

  @Column({ type: 'numeric', nullable: true })
  salary_max: number;

  @Column({ type: 'date', nullable: true })
  deadline: string;

  @Column({ type: 'boolean', default: true })
  is_active: boolean;

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamptz' })
  updated_at: Date;
}

/**
 * 9. Journal
 */
@Entity({ name: 'journals' })
export class Journal {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text' })
  image: string;

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;
}

/**
 * 10. News
 */
@Entity({ name: 'news' })
export class News {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text' })
  title_uz: string;

  @Column({ type: 'text', nullable: true })
  title_ru: string;

  @Column({ type: 'text', nullable: true })
  title_en: string;

  @Column({ type: 'text', nullable: true })
  body_uz: string;

  @Column({ type: 'text', nullable: true })
  body_ru: string;

  @Column({ type: 'text', nullable: true })
  body_en: string;

  @Column('text', { array: true, default: '{}' })
  images: string[];

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamptz' })
  updated_at: Date;

  @Column({ type: 'timestamptz', nullable: true })
  published_at: Date;
}

/**
 * 11. Gallery
 */
@Entity({ name: 'gallery' })
export class Gallery {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'enum', enum: ['image', 'video'] })
  media_type: 'image' | 'video';

  @Column({ type: 'text', nullable: true })
  title: string;

  @Column({ type: 'text' })
  url: string;

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;
}

/**
 * 12. UserEmail
 */
@Entity({ name: 'useremails' })
export class UserEmail {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'text', unique: true })
  email: string;

  @CreateDateColumn({ type: 'timestamptz' })
  created_at: Date;
}