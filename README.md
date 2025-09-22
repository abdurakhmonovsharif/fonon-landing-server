# Fonon Landing Server (Java)

Spring Boot port of the Fonon landing server originally implemented with Fastify + TypeORM. This project mirrors the Node.js API surface, data model, and Docker setup while leveraging Spring Boot, Spring Data JPA, and PostgreSQL.

## Features
- RESTful CRUD endpoints for all domain models (`Location`, `Appointment`, `NavItem`, etc.) exposed under `/api`.
- Reusable generic CRUD service + controller base classes to minimize boilerplate.
- PostgreSQL persistence via Spring Data JPA and Hibernate.
- Enum parity with the TypeScript implementation (`AppointmentType`, `MediaType`).
- OpenAPI documentation served through Springdoc at `/docs`.
- Health check at `/health` and root greeting at `/` mirroring the Fastify endpoints.

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+
- PostgreSQL 13+

### Environment Variables
Configure the same environment variables used by the Node.js service:

| Variable | Description | Default         |
| --- | --- |-----------------|
| `PORT` | HTTP server port | `8080`          |
| `DB_HOST` | PostgreSQL host | `localhost`     |
| `DB_PORT` | PostgreSQL port | `5432`          |
| `DB_NAME` | Database name | `fonon_landing` |
| `DB_USER` | Database user | `postgres`      |
| `DB_PASS` | Database password | `root`          |

### Run Locally
```bash
mvn spring-boot:run
```

The API is available at `http://localhost:8080/api`.

### Build a Jar
```bash
mvn clean package
java -jar target/fonon-landing-server-1.0.0.jar
```

### Docker
```bash
docker build -t fonon-landing-server-java .
docker run --rm -p 8080:8080 \
  -e DB_HOST=postgres \
  -e DB_PORT=5432 \
  -e DB_NAME=fonon_landing \
  -e DB_USER=postgres \
  -e DB_PASS=root \
  fonon-landing-server-java
```

Mount your own `application.yml` or use environment variables for production configuration.

## Project Structure
```
src/main/java/com/fonon/landingserver
├── FononLandingServerApplication.java
├── controller/           # REST controllers (CRUD + health/hello)
├── domain/               # JPA entities and enums
├── exception/            # Global exception handling
├── repository/           # Spring Data repositories
├── service/              # Generic + per-entity services
└── web/dto/              # Response DTOs
```

```
src/main/resources
└── application.yml       # Spring Boot configuration mirroring Node env vars
```

## API Docs
Springdoc exposes OpenAPI JSON at `/docs/api-docs` and Swagger UI at `/docs`.

## Notes
- Array columns from PostgreSQL (`text[]`) leverage Hypersistence Utils `ListArrayType` for parity with the TypeScript arrays.
- CRUD endpoints operate on the entity graph. Provide nested entities with IDs to wire relationships.
- Update operations ignore `null` fields to match the TypeORM `preload` behavior.

## License
MIT — same as the original project.
# fonon-landing-server
