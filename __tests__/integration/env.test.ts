import { resolve } from 'path';
import { config } from 'dotenv';

describe('Environment Variables', () => {
  beforeAll(() => {
    // Load environment variables from .env file
    config({ path: resolve(__dirname, '../../.env') });
  });

  it('should have required environment variables defined', () => {
    expect(process.env.PORT).toBeDefined();
    expect(process.env.DATABASE_URL).toBeDefined();
    expect(process.env.OPENAI_API_KEY).toBeDefined();
  });

  it('should have PORT as a valid number', () => {
    const port = parseInt(process.env.PORT || '', 10);
    expect(Number.isInteger(port)).toBe(true);
    expect(port).toBeGreaterThan(0);
  });

  it('should have DATABASE_URL as a valid URL string', () => {
    const dbUrl = process.env.DATABASE_URL;
    expect(typeof dbUrl).toBe('string');
    expect(dbUrl?.length).toBeGreaterThan(0);
    // Basic URL validation
    expect(() => new URL(dbUrl as string)).not.toThrow();
  });
});
