import type { ColumnMetadata } from 'typeorm/metadata/ColumnMetadata';

import { AppDataSource } from '@/config/database';
import { crudEntities } from '@/config/entities';

type SchemaObject = Record<string, any>;
type PathsObject = Record<string, unknown>;

const mapColumnToSchema = (column: ColumnMetadata): SchemaObject => {
  const baseSchema = (): SchemaObject => {
    if (column.enum && column.enum.length > 0) {
      const enumType = typeof column.enum[0] === 'number' ? 'number' : 'string';
      return { type: enumType, enum: column.enum };
    }

    const columnType = typeof column.type === 'string' ? column.type : column.type.name.toLowerCase();

    switch (columnType) {
      case 'int':
      case 'integer':
      case 'bigint':
      case 'smallint':
        return { type: 'integer', format: 'int64' };
      case 'numeric':
      case 'float':
      case 'double':
      case 'decimal':
        return { type: 'number', format: 'float' };
      case 'boolean':
        return { type: 'boolean' };
      case 'date':
        return { type: 'string', format: 'date' };
      case 'time':
        return { type: 'string', format: 'time' };
      case 'timestamp':
      case 'timestamptz':
      case 'datetime':
        return { type: 'string', format: 'date-time' };
      default:
        return { type: 'string' };
    }
  };

  const schema = column.isArray
    ? { type: 'array', items: baseSchema() }
    : baseSchema();

  if (column.isNullable) {
    schema.nullable = true;
  }

  if (isReadOnly(column)) {
    schema.readOnly = true;
  }

  return schema;
};

const isReadOnly = (column: ColumnMetadata): boolean =>
  column.isPrimary || column.isGenerated || column.isCreateDate || column.isUpdateDate;

const buildEntitySchema = (_entityName: string, columns: ColumnMetadata[]): SchemaObject => {
  const properties: Record<string, SchemaObject> = {};

  for (const column of columns) {
    properties[column.propertyName] = mapColumnToSchema(column);
  }

  return {
    type: 'object',
    properties,
    additionalProperties: false,
    required: columns
      .filter((column) => !column.isNullable && !isReadOnly(column) && column.default === undefined)
      .map((column) => column.propertyName),
  };
};

const buildCreateSchema = (columns: ColumnMetadata[], entitySchema: SchemaObject): SchemaObject => {
  const properties = { ...(entitySchema.properties as Record<string, SchemaObject>) };

  for (const [key, value] of Object.entries(properties)) {
    if (value.readOnly) {
      delete properties[key];
    }
  }

  const required = columns
    .filter((column) => !column.isNullable && !isReadOnly(column) && column.default === undefined)
    .map((column) => column.propertyName)
    .filter((name) => properties[name]);

  return {
    type: 'object',
    properties,
    additionalProperties: false,
    required,
  };
};

const buildUpdateSchema = (entitySchema: SchemaObject): SchemaObject => {
  const properties = { ...(entitySchema.properties as Record<string, SchemaObject>) };

  for (const [key, value] of Object.entries(properties)) {
    if (value.readOnly) {
      delete properties[key];
    } else {
      delete value.readOnly;
    }
  }

  return {
    type: 'object',
    properties,
    additionalProperties: false,
  };
};

export const buildOpenApiSpec = (): Record<string, unknown> => {
  const paths: PathsObject = {};
  const schemas: Record<string, SchemaObject> = {};

  for (const config of crudEntities) {
    const metadata = AppDataSource.getRepository(config.entity).metadata;
    const columns = metadata.columns;
    const schemaName = `${config.name}`;
    const createSchemaName = `${config.name}Create`;
    const updateSchemaName = `${config.name}Update`;

    const entitySchema = buildEntitySchema(schemaName, columns);
    const createSchema = buildCreateSchema(columns, entitySchema);
    const updateSchema = buildUpdateSchema(entitySchema);

    schemas[schemaName] = entitySchema;
    schemas[createSchemaName] = createSchema;
    schemas[updateSchemaName] = updateSchema;

    const collectionPath = config.basePath;
    const itemPath = `${config.basePath}/{id}`;

    paths[collectionPath] = {
      get: {
        tags: [config.name],
        summary: `List ${config.name}`,
        responses: {
          200: {
            description: `Array of ${config.name}`,
            content: {
              'application/json': {
                schema: {
                  type: 'array',
                  items: { $ref: `#/components/schemas/${schemaName}` },
                },
              },
            },
          },
        },
      },
      post: {
        tags: [config.name],
        summary: `Create ${config.name}`,
        requestBody: {
          required: true,
          content: {
            'application/json': {
              schema: { $ref: `#/components/schemas/${createSchemaName}` },
            },
          },
        },
        responses: {
          201: {
            description: `${config.name} created`,
            content: {
              'application/json': {
                schema: { $ref: `#/components/schemas/${schemaName}` },
              },
            },
          },
        },
      },
    };

    paths[itemPath] = {
      parameters: [
        {
          name: 'id',
          in: 'path',
          required: true,
          schema: { type: 'integer', format: 'int64' },
        },
      ],
      get: {
        tags: [config.name],
        summary: `Get ${config.name} by id`,
        responses: {
          200: {
            description: `${config.name} details`,
            content: {
              'application/json': {
                schema: { $ref: `#/components/schemas/${schemaName}` },
              },
            },
          },
          404: { description: `${config.name} not found` },
        },
      },
      put: {
        tags: [config.name],
        summary: `Update ${config.name}`,
        requestBody: {
          required: true,
          content: {
            'application/json': {
              schema: { $ref: `#/components/schemas/${updateSchemaName}` },
            },
          },
        },
        responses: {
          200: {
            description: `${config.name} updated`,
            content: {
              'application/json': {
                schema: { $ref: `#/components/schemas/${schemaName}` },
              },
            },
          },
          404: { description: `${config.name} not found` },
        },
      },
      delete: {
        tags: [config.name],
        summary: `Delete ${config.name}`,
        responses: {
          204: { description: `${config.name} deleted` },
          404: { description: `${config.name} not found` },
        },
      },
    };
  }

  return {
    openapi: '3.1.0',
    info: {
      title: 'Fonon Landing API',
      version: '1.0.0',
    },
    servers: [{ url: 'http://localhost:5000/api', description: 'Development server' }],
    paths,
    components: {
      schemas,
    },
  };
};
