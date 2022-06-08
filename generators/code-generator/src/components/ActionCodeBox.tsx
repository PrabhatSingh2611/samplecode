import { Prism } from "@mantine/prism";
import { Title } from "@mantine/core";
import { Schema } from "../meta-models";
import { ReactGraphQLGetOneGenerator, ReactHookGetOne } from "../generators";
import GraphQLIcon from "./Icons/GraphQLIcon";
import TSIcon from "./Icons/TSIcon";
import { buildNameVariations } from "../engines/name-variations";

interface IProps {
  // TODO: Change to EAction = { "GET_ONE", "GET_MANY", "GET_MANY_CONNECTION", "CREATE", "UPDATE", "PATCH", "DELETE" }
  action: string;
  schema: Schema;
  events: string[];
  title: string;
}

const ActionCodeBox = ({ action, schema, events, title }: IProps) => {
  if (!schema) {
    return null;
  }

  const { ref } = buildNameVariations(schema);

  const graphqlTemplate = ReactGraphQLGetOneGenerator.generate(
    events,
    schema
  ).template;
  const reactHookTemplate = ReactHookGetOne.generate(events, schema).template;

  return (
    <div className="code-block">
      <Title order={3}>{title}</Title>
      <br />
      <Prism.Tabs>
        <Prism.Tab
          label={`get-${ref}-for-details-page.graphql`}
          language="graphql"
          withLineNumbers
          icon={<GraphQLIcon />}
        >
          {graphqlTemplate}
        </Prism.Tab>
        <Prism.Tab
          label={`get-${ref}-for-details-page.hook.ts`}
          language="typescript"
          withLineNumbers
          icon={<TSIcon />}
        >
          {reactHookTemplate}
        </Prism.Tab>
      </Prism.Tabs>
    </div>
  );
};

export default ActionCodeBox;
