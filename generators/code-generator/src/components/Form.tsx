import { TextInput, Textarea, Button, Group, Box } from "@mantine/core";
import { useForm } from "@mantine/form";

const Form = ({ onChange }) => {
  const form = useForm({
    initialValues: {
      model: "",
      modelPlural: "",
      eventsString: "",
      modelFieldsString: "",
    },
  });

  const handleSubmit = ({
    model,
    modelPlural,
    modelFieldsString,
    eventsString,
  }) => {
    const events = eventsString.split(", ");
    const modelFields = modelFieldsString
      .split("\n")
      .map((field) => field.trim());
    onChange({
      model,
      modelPlural,
      events,
      modelFields,
    });
  };

  return (
    <Box sx={{ maxWidth: 500 }} mx="auto">
      <form onSubmit={form.onSubmit(handleSubmit)}>
        <TextInput
          required
          label="Model"
          placeholder="user"
          description="Name of your Model in single."
          {...form.getInputProps("model")}
        />
        <br />
        <TextInput
          required
          label="Model Plural"
          placeholder="users"
          description="Name of your Model in plural."
          {...form.getInputProps("modelPlural")}
        />
        <br />
        <Textarea
          label="Events"
          placeholder="login, logout"
          description="Actions could be applied to your Model, coma separated."
          {...form.getInputProps("eventsString")}
        />
        <br />
        <Textarea
          label="Model Fields"
          placeholder="id: UUID!&#10;firstName: String!&#10;lastName: String!&#10;age: Int"
          autosize
          minRows={4}
          description="Fields your Model consists of. Each one on the new line!"
          {...form.getInputProps("modelFieldsString")}
        />

        <Group position="right" mt="md">
          <Button type="submit">Generate</Button>
        </Group>
      </form>
    </Box>
  );
};

export default Form;
