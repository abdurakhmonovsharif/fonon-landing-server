import { HelloResponse } from '../interfaces/hello.interface';

export class HelloController {
  public async getHello(): Promise<HelloResponse> {
    return {
      message: 'Hello World!',
    };
  }
}
