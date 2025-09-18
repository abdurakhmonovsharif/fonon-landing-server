# Fonon Landing Server

A TypeScript Fastify server that powers the Fonon landing experience. The service exposes RESTful CRUD endpoints backed by PostgreSQL via TypeORM and ships with optional Scalar-powered API documentation.

## Highlights

- **Fastify + TypeScript** for a fast, strongly-typed HTTP layer.
- **TypeORM** entities and services for every domain model (`Location`, `Appointment`, `NavItem`, and more).
- **Modular architecture** with per-entity controllers, services, and route plugins.
- **Scalar API Reference** (optional) to serve interactive OpenAPI docs at `/docs`.
- **Jest**, **ESLint**, and **Prettier** wired into npm scripts for quality and consistency.

## Tech Stack

| Area | Tools |
| ---- | ----- |
| Runtime | Node.js 22, Fastify 5 |
| Database | PostgreSQL, TypeORM |
| Language | TypeScript 5 |
| Tooling | tsx, Nodemon, ESLint, Prettier, Jest |

## Project Layout

```
├── src/
│   ├── app.ts                 # Fastify factory and route registration
│   ├── config/                # Data source and entity configuration
│   ├── controllers/           # Feature controllers (wrapping generic CRUD)
│   ├── crud/                  # Shared CRUD helpers (service, controller base, errors)
│   ├── docs/                  # OpenAPI generator
│   ├── models/                # TypeORM entities
│   ├── plugins/               # Fastify plugins (Scalar docs, etc.)
│   ├── routes/                # Per-entity route plugins
│   ├── services/              # Per-entity service factories
│   └── types/                 # Ambient module declarations
├── init.sql                   # Optional DB bootstrap script
├── server.ts                  # Application entry point
├── package.json               # Scripts and dependencies
└── tsconfig.json              # TypeScript compiler options
```

## Prerequisites

- Node.js 20 or newer (project currently uses Node 22).
- PostgreSQL 13+ running locally or accessible via network.

## Getting Started

1. **Clone the repository**
   ```bash
   git clone <your-fork-or-origin>
   cd fonon-landing-server
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Configure environment variables**
   - Duplicate `.env.developement` (yes, intentional spelling) to `.env.development.local` or `.env` and adjust credentials.
   - Key variables: `DB_HOST`, `DB_PORT`, `DB_USER`, `DB_PASS`, `DB_NAME`, `PORT`.

4. **Provision the database (optional)**
   - Use `init.sql` as a starting point, or rely on TypeORM `synchronize: true` for development.

5. **Run the dev server**
   ```bash
   npm run dev
   ```
   Fastify listens on `http://localhost:5000` by default.

## API Documentation

Scalar can serve interactive docs at `/docs`:

```bash
npm install @scalar/fastify-api-reference
npm run dev
```

- Visit `http://localhost:5000/docs/` for the UI (note the trailing slash).
- Scalar is optional; if the dependency is missing the server will skip the plugin gracefully.

## Useful Scripts

| Command | Description |
| ------- | ----------- |
| `npm run dev` | Start the nodemon + tsx development server |
| `npm run start` | Start the production build (tsx) |
| `npm run type-check` | TypeScript type checking only |
| `npm run lint` | Run ESLint |
| `npm run format` | Format with Prettier |
| `npm test` | Run Jest test suite |
| `npm run migration:generate` | Generate a TypeORM migration from entity changes |
| `npm run migration:run` | Execute pending TypeORM migrations |

## Architecture Overview

- **Controllers & Services**: Each entity has a service (wrapping the generic `CrudService`) and controller (wrapping the generic `CrudController`). Route plugins compose these with Fastify paths under `/api/<resource>`.
- **Data Source**: `src/config/database.ts` exposes a shared `AppDataSource` and `initializeDataSource()` helper used throughout.
- **OpenAPI**: `src/docs/openapi.ts` inspects TypeORM metadata to produce a live OpenAPI 3.1 specification consumed by Scalar.

## Testing

The project uses Jest. Tests live under `__tests__/`.

```bash
npm test            # run all tests once
npm run test:watch  # watch mode
npm run test:coverage
```

## Contributing

1. Fork the repo.
2. Create a feature branch: `git checkout -b feature/my-update`.
3. Commit with descriptive messages.
4. Submit a pull request once your feature is ready.

## License

Released under the MIT License. See [LICENSE](LICENSE) for details.

## Maintainer

Crafted by Abdurakhmonov Sharif and contributors.
