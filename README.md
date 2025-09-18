# Node.js Fastify PostgreSQL Template

A modern, production-ready Node.js template using Fastify and PostgreSQL for quick project setup. This template provides a solid foundation for building scalable and maintainable applications with TypeScript.

## 🚀 Features

- **Fastify** - High-performance web framework
- **TypeScript** - Type-safe JavaScript
- **PostgreSQL** - Robust relational database
- **Jest** - Testing framework
- **ESLint + Prettier** - Code quality and formatting
- **Husky** - Git hooks for pre-commit checks
- **Docker** - Containerization support
- **MVC Architecture** - Clean and organized code structure

## 🛠️ Tech Stack

### Core

- Node.js
- Fastify
- PostgreSQL
- TypeScript

### Development Tools

- Jest (Testing)
- ESLint (Linting)
- Prettier (Code formatting)
- Husky (Git hooks)
- Nodemon (Development server)

### Type Definitions

- @types/node
- @types/jest
- @types/pg
- @types/supertest

## 📁 Project Structure

```
├── src/
│   ├── config/         # Configuration files
│   ├── controllers/    # Route controllers
│   ├── models/        # Database models
│   ├── routes/        # API routes
│   ├── services/      # Business logic
│   ├── types/         # TypeScript type definitions
│   └── utils/         # Utility functions
├── __tests__/         # Test files
│   ├── integration/   # Integration tests
│   └── unit/         # Unit tests
├── server.ts         # Application entry point
└── package.json      # Project dependencies
```

## 🚀 Getting Started

1. Clone the repository:

```bash
git clone git@github.com:g-guerzoni/node-fastify-template.git
```

2. Install dependencies:

```bash
npm install
```

3. Set up environment variables:

```bash
cp .env.example .env
# Edit .env with your configuration
```

4. Start the development server:

```bash
npm run dev
```

## 📝 Available Scripts

- `npm start` - Start the production server
- `npm run dev` - Start the development server with hot-reload
- `npm test` - Run tests
- `npm run test:watch` - Run tests in watch mode
- `npm run test:coverage` - Run tests with coverage report
- `npm run lint` - Run ESLint
- `npm run format` - Format code with Prettier
- `npm run type-check` - Check TypeScript types

## 🧪 Testing

The project uses Jest for testing. Tests are organized into:

- Unit tests (`__tests__/unit/`)
- Integration tests (`__tests__/integration/`)

Run tests with:

```bash
npm test
```

## 🔧 Configuration

- `tsconfig.json` - TypeScript configuration
- `.eslintrc.json` - ESLint configuration
- `.prettierrc` - Prettier configuration
- `jest.config.ts` - Jest configuration

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

Guilherme Guerzoni

## 🙏 Acknowledgments

- Fastify team for the amazing framework
- All contributors and maintainers of the used packages
# fonon-landing-server
