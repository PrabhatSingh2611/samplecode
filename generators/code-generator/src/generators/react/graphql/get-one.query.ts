import { buildNameVariations } from "../../../engines/name-variations";
import { Schema, Generator } from "../../../meta-models";

const generate = (eventNames: string[], schema: Schema) => {
  const { model, ref } = buildNameVariations(schema);

  const template = `query get${model}For${model}DetailsPage($input: ${model}Input!) {
    ${ref}(input: $input) {
        ...${model}For${model}DetailsPage
    }
}

fragment ${model}For${model}DetailsPage on ${model} {
    id
    # Add your fields here
}`;

  return {
    template,
  };
};

export const ReactGraphQLGetOneGenerator: Generator = {
  generate,
};
