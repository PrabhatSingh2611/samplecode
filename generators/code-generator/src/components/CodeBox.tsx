import { Prism } from "@mantine/prism";
import { Title } from "@mantine/core";
import { Generator, Schema } from "../meta-models";

interface IProps {
  generator: Generator;
  schema: Schema;
  events: string[];
  title: string;
  language: string;
}

const CodeBox = ({ generator, schema, events, title, language }: IProps) => {
  if (!generator || !schema) {
    return null;
  }

  const template = generator.generate(events, schema).template;

  return (
    <div className="code-block">
      <Title order={2}>{title}</Title>
      <br />
      <Prism language={language as any} colorScheme="dark" withLineNumbers>
        {template}
      </Prism>
    </div>
  );
};

export default CodeBox;
