import {
  buildNameVariations,
  buildEventVariations,
} from "../engines/name-variations";
import { Schema } from "../meta-models";

const generate = (eventName: string, schema: Schema) => {
  const { model } = buildNameVariations(schema);
  const { label, title } = buildEventVariations(eventName);

  const template = `

# ${title} Mutation

type Mutation {
    ${label}${model}(input: ${title}${model}Input!): ${model}Payload!
}

input ${title}${model}Input {
    id: UUID!
}`;

  return {
    template,
  };
};

export const APISchemaEventGenerator = {
  generate,
};
