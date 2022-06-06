import { buildNameVariations } from "../engines/name-variations";
import { Schema, Generator } from "../meta-models";

const generate = (eventNames: string[], schema: Schema) => {
  const { model, models, ref, refs } = buildNameVariations(schema);

  const template = `# <<< QUERIES >>>

# Single
query get${model}For${model}DetailsPage($input: ${model}Input!) {
    ${ref}(input: $input) {
        ...${model}For${model}DetailsPage
    }
}

fragment ${model}For${model}DetailsPage on ${model} {
    id
    # Add your fields here
}

# Multiple (Connection)
query get${models}For${models}ListPage($input: ${models}Input!) {
    ${refs}(input: $input) {
        items {
            ...${model}For${models}ListPage
        }
        pageInfo {
            ...PageInfo
        }
        totalItems
    }
}

fragment ${model}For${models}ListPage on ${model} {
    id
    # Add your fields here
}

# <<< MUTATIONS >>>

# Create
mutation create${model}ForCreateModal($input: Create${model}Input!) {
    create${model}(input: $input) {
        ...${model}ForCreateModal
    }
}

fragment ${model}ForCreateModal on ${model} {
    id
    # Add your fields here
}

# Patch
mutation patch${model}ForUpdateModal($input: Patch${model}Input!) {
    patch${model}(input: $input) {
        ...${model}ForUpdateModal
    }
}

fragment ${model}ForUpdateModal on ${model} {
    id
    # Add your fields here
}

# Update
mutation update${model}ForUpdateModal($input: Update${model}Input!) {
    update${model}(input: $input) {
        ...${model}ForUpdateModal
    }
}

fragment ${model}ForUpdateModal on ${model} {
    id
    # Add your fields here
}

# Delete
mutation delete${model}($input: Delete${model}Input!) {
    delete${model}(input: $input) {
        id
    }
}
`;

  return {
    template,
  };
};

export const GraphQLDocumentGenerator: Generator = {
  generate,
};
