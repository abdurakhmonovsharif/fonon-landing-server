import { HealthResponse } from '../interfaces/health.interface';

export class HealthController {
  public async getHealth(): Promise<HealthResponse> {
    return {
      status: 'ok',
      timestamp: new Date().toISOString(),
    };
  }
}
