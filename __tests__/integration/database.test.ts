import pool from '../../src/config/database';

describe('Database Integration', () => {
  // Test database connection
  it('should connect to the database successfully', async () => {
    try {
      const client = await pool.connect();
      expect(client).toBeDefined();
      client.release();
    } catch (error) {
      fail('Database connection failed: ' + error);
    }
  });

  // Test basic query execution
  it('should execute a simple query', async () => {
    try {
      const result = await pool.query('SELECT NOW()');
      expect(result.rows).toBeDefined();
      expect(result.rows.length).toBe(1);
    } catch (error) {
      fail('Query execution failed: ' + error);
    }
  });

  // Test database version
  it('should return PostgreSQL version', async () => {
    try {
      const result = await pool.query('SELECT version()');
      expect(result.rows[0].version).toContain('PostgreSQL');
    } catch (error) {
      fail('Version query failed: ' + error);
    }
  });

  // Cleanup after all tests
  afterAll(async () => {
    await pool.end();
  });
});
