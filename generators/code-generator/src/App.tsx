import { useState } from "react";
import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";
import { dracula } from "react-syntax-highlighter/dist/esm/styles/prism";
import { CopyToClipboard } from "react-copy-to-clipboard";

import Form from "./Form";
import {
  APISchemaGenerator,
  GraphQLDocumentGenerator,
  HookGenerator,
} from "./generators";
import { Schema } from "./meta-models";
import { buildNameVariations } from "./engines/name-variations";

const App = () => {
  const [valid, setValid] = useState(false);

  const [apiSchemaTemplate, setApiSchemaTemplate] = useState("");
  const [apiSchemaTemplateCopied, setApiSchemaTemplateCopied] = useState(false);

  const [graphqlDocumentTemplate, setGraphqlDocumentTemplate] = useState("");
  const [graphqlDocumentTemplateCopied, setGraphqlDocumentTemplateCopied] =
    useState(false);

  const [hookTemplate, setHookTemplate] = useState("");
  const [hookTemplateCopied, setHookTemplateCopied] = useState(false);

  const onFormChange = ({ model, modelPlural, modelFields, events }) => {
    setApiSchemaTemplateCopied(false);
    setGraphqlDocumentTemplateCopied(false);

    if (model && modelPlural) {
      const schema: Schema = {
        model,
        modelPlural,
        fields: modelFields,
      };
      const names = buildNameVariations(schema);
      console.log("Names: ", JSON.stringify(names, null, 2));
      const validEvents = events?.[0] ? events : [];

      const apiSchemaTemplate = APISchemaGenerator.generate(
        validEvents,
        schema
      ).template;
      setApiSchemaTemplate(apiSchemaTemplate);

      const graphqlDocumentTemplate = GraphQLDocumentGenerator.generate(
        validEvents,
        schema
      ).template;
      setGraphqlDocumentTemplate(graphqlDocumentTemplate);

      const hookTemplate = HookGenerator.generate(validEvents, schema).template;
      setHookTemplate(hookTemplate);

      setValid(true);
    } else {
      setApiSchemaTemplate("");
      setValid(false);
    }
  };

  return (
    <div>
      <div>
        <Form onChange={onFormChange} />
      </div>
      {valid ? (
        <div>
          <div className="code-block">
            <h2>API Schema</h2>
            <SyntaxHighlighter
              language="graphql"
              style={dracula}
              showLineNumbers
              wrapLongLines
            >
              {apiSchemaTemplate}
            </SyntaxHighlighter>
            <br />
            <CopyToClipboard
              text={apiSchemaTemplate}
              onCopy={() => setApiSchemaTemplateCopied(true)}
            >
              {apiSchemaTemplateCopied ? (
                <button className="outline">✅ Copied</button>
              ) : (
                <button>Copy</button>
              )}
            </CopyToClipboard>
            <hr />
          </div>

          <div className="code-block">
            <h2>GraphQL Documents</h2>
            <SyntaxHighlighter
              language="graphql"
              style={dracula}
              showLineNumbers
              wrapLongLines
            >
              {graphqlDocumentTemplate}
            </SyntaxHighlighter>
            <br />
            <CopyToClipboard
              text={graphqlDocumentTemplate}
              onCopy={() => setGraphqlDocumentTemplateCopied(true)}
            >
              {graphqlDocumentTemplateCopied ? (
                <button className="outline">✅ Copied</button>
              ) : (
                <button>Copy</button>
              )}
            </CopyToClipboard>
            <hr />
          </div>

          <div className="code-block">
            <h2>Hooks</h2>
            <SyntaxHighlighter
              language="typescript"
              style={dracula}
              showLineNumbers
              wrapLongLines
            >
              {hookTemplate}
            </SyntaxHighlighter>
            <br />
            <CopyToClipboard
              text={hookTemplate}
              onCopy={() => setHookTemplateCopied(true)}
            >
              {hookTemplateCopied ? (
                <button className="outline">✅ Copied</button>
              ) : (
                <button>Copy</button>
              )}
            </CopyToClipboard>
            <hr />
          </div>
        </div>
      ) : (
        <></>
      )}
    </div>
  );
};

export default App;
