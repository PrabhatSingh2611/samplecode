import { buildNameVariations } from "../engines/name-variations";
import { Schema, Generator } from "../meta-models";
import { APISchemaEventGenerator } from "./api-schema-event";

const generate = (eventNames: string[], schema: Schema) => {
  const { model, models, ref, refs } = buildNameVariations(schema);
  const eventTemplates = eventNames.map((eventName) =>
    APISchemaEventGenerator.generate(eventName, schema)
  );
  const { originalFields, createFields, patchFields } =
    generateFiledsStrings(schema);

  const generalTemplate = {
    template: `# <<< MODEL >>>

type ${model} implements Node {
    ${originalFields}
}
    
# <<< QUERIES >>>

# Single
type Query {
    ${ref}(input: ${model}Input!): ${model}Payload!
}

input ${model}Input {
    id: UUID!
}

type ${model}Paylod {
    ${ref}: ${model}
}

# -----------------------

# Multiple (Connection)
type Query {
    ${refs}(input: ${models}Input!): ${models}ConnnectionPayload!
}

input ${models}Input {
    where: ${models}WhereInput
    pagination: PageInput
    sort: [${model}Sort!] = createdAt_DESC
}

input ${models}WhereInput {
    # Add your fields here
}

enum ${model}Sort {
    createdAt_DESC
    createdAt_ASC
}

type ${model}ConnectionPayload implements ConnectionPayload {
    items: [${model}!]!
    pageInfo: PageInfo!
    totalItems: Int!
}

# -----------------------

# Multiple (List)
type Query {
    ${refs}(input: ${models}Input!): ${models}Payload!
}

input ${models}Input {
    where: ${models}WhereInput
    sort: [${model}Sort!] = createdAt_DESC
}

input ${models}WhereInput {
    # Add your fields here
}

enum ${model}Sort {
    createdAt_DESC
    createdAt_ASC
}

type ${models}Payload {
    ${refs}: [${model}!]!
}

# =======================

# <<< GENERAL MUTATIONS >>>

# Create
type Mutation {
    create${model}(input: Create${model}Input!): ${model}Payload!
}

input Create${model}Input {
    # Add your fields here
    ${createFields}
}

# -----------------------

# Patch
type Mutation {
    patch${model}(input: Patch${model}Input!): ${model}Payload!
}

input Patch${model}Input {
    id: UUID!
    ${patchFields}
}

# -----------------------

# Update
type Mutation {
    update${model}(input: Update${model}Input!): ${model}Payload!
}

input Update${model}Input {
    id: UUID!
    ${patchFields}
}

# -----------------------

# Delete
type Mutation {
    delete${model}(input: Delete${model}Input!): Delete${model}Payload!
}

input Delete${model}Input {
    id: UUID!
}

type Delete${model}Payload {
    ${ref}: Node
}

# =======================

# <<< CUSTOM MUTATIONS >>>`,
  };

  const template = [generalTemplate, ...eventTemplates].reduce((acc, curr) => {
    return (acc += curr.template);
  }, "");

  return {
    template,
  };
};

const generateFiledsStrings = (schema: Schema) => {
  return {
    originalFields: schema.fields.join("\n    "),
    patchFields: schema.fields
      .filter((field) => field !== "id: UUID!")
      .join("\n    ")
      .replace("!", ""),
    createFields: schema.fields
      .filter((field) => field !== "id: UUID!")
      .join("\n    "),
  };
};

export const APISchemaGenerator: Generator = {
  generate,
};
