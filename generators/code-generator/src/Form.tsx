import { useEffect } from "react";
import { useState } from "react";

const Form = ({ onChange }) => {
  const [model, setModel] = useState("");
  const [modelPlural, setModelPlural] = useState("");
  const [eventsString, setEvents] = useState("");
  const [modelFieldsString, setModelFieldsString] = useState("");

  useEffect(() => {
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
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [model, modelPlural, modelFieldsString, eventsString]);

  return (
    <form>
      <div className="grid">
        <label>
          Model:
          <input
            name="model"
            type="text"
            value={model}
            placeholder="user"
            required
            onChange={(event) => setModel(event.target.value)}
          />
        </label>
        <label>
          Model Plural:
          <input
            name="modelPlural"
            type="text"
            value={modelPlural}
            placeholder="users"
            required
            onChange={(event) => setModelPlural(event.target.value)}
          />
        </label>
      </div>
      <label>
        {/* <label style={{ display: "none" }}> */}
        Model Fields:
        <textarea
          value={modelFieldsString}
          rows={8}
          placeholder="id: UUID!&#10;firstName: String!&#10;lastName: String!"
          onChange={(event) => setModelFieldsString(event.target.value)}
        />
      </label>
      <label>
        Actions:
        <textarea
          value={eventsString}
          placeholder="login, logout"
          onChange={(event) => setEvents(event.target.value)}
        />
      </label>
    </form>
  );
};

export default Form;
