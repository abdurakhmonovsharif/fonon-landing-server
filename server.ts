import { build } from '@/app';
import { AppDataSource, initializeDataSource } from '@/config/database';
import { Location } from '@/models/models';
import dotenv from 'dotenv';
import 'reflect-metadata';
// Load environment variables
dotenv.config();

const start = async (): Promise<void> => {
  await initializeDataSource();
  const server = await build();
  try {
    const port = parseInt(process.env.PORT || '5000', 10);
    await server.listen({ port });
    console.log(`Server is running on http://localhost:${port}`);
  } catch (err) {
    server.log.error(err);
    process.exit(1);
  }
};

start().catch((err) => {
  console.error('Failed to start server', err);
  process.exit(1);
});
