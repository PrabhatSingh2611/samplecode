import { useState } from "react";

import Form from "./components/Form";
import {
  APISchemaGenerator,
  GraphQLDocumentGenerator,
  HookGenerator,
} from "./generators";
import { Schema } from "./meta-models";
import { buildNameVariations } from "./engines/name-variations";
import CodeBox from "./components/CodeBox";

const App = () => {
  const [schema, setSchema] = useState<Schema | null>(null);
  const [events, setEvents] = useState<string[] | null>(null);

  const onFormChange = ({ model, modelPlural, modelFields, events }) => {
    if (model && modelPlural) {
      const schema: Schema = {
        model,
        modelPlural,
        fields: modelFields,
      };
      setSchema(schema);
      setEvents(events?.[0] ? events : []);

      console.log(
        "Names: ",
        JSON.stringify(buildNameVariations(schema), null, 2)
      );
    }
  };

  return (
    <div>
      <div>
        <Form onChange={onFormChange} />
      </div>

      <div>
        <CodeBox
          generator={APISchemaGenerator}
          schema={schema}
          events={events}
          language="graphql"
          title="API Schema Design"
        />
        <CodeBox
          generator={GraphQLDocumentGenerator}
          schema={schema}
          events={events}
          language="graphql"
          title="GraphQL Documents"
        />
        <CodeBox
          generator={HookGenerator}
          schema={schema}
          events={events}
          language="typescript"
          title="Hooks"
        />
      </div>
    </div>
  );
};

export default App;
