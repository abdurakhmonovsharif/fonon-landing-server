export class HttpError extends Error {
  constructor(public statusCode: number, message: string) {
    super(message);
    this.name = 'HttpError';
  }
}

export const notFound = (resource: string, id: number | string): HttpError =>
  new HttpError(404, `${resource} with id ${id} not found`);

export const badRequest = (message: string): HttpError => new HttpError(400, message);
