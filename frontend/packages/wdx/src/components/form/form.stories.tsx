import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import * as Yup from 'yup';
import WForm from './index';
import { WMenuItem } from '../menu';
import { WAdapterDateFns, WLocalizationProvider } from '../date-picker';
import WTextField from '../text-field';

const meta: Meta = {
  title: 'MUI/Inputs/Form',
  component: WForm,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const TemplateForm: Story = () => {
  const validation = Yup.object().shape({
    input: Yup.string().required('Name is required'),
    animal: Yup.string().required('Animal is required'),
    upload: Yup.object().required('Upload is required'),
    date: Yup.date().required('Date is required'),
  });

  const defaultValues = {
    input: 'initial value',
    date: null,
  };

  return (
    <WBox display="flex" flexWrap="wrap" gap={2}>
      <WForm
        id="form"
        onSubmit={console.log}
        validation={validation}
        defaultValues={defaultValues}
      >
        <WBox
          display="flex"
          flexDirection="column"
          flexWrap="wrap"
          gap={2}
          sx={{ width: 500 }}
        >
          <WForm.Control>
            <WForm.Input name="input" label="My input" />
          </WForm.Control>

          <WForm.Control>
            <WForm.Input
              multiline={true}
              name="textarea"
              label="My textarea"
              minRows={3}
            />
          </WForm.Control>

          <WForm.Control>
            <WForm.Select name="animal" label="Animal" placeholder="Animal">
              <WMenuItem value="cat">Cat</WMenuItem>
              <WMenuItem value="dog">Dog</WMenuItem>
              <WMenuItem value="cow">Cow</WMenuItem>
            </WForm.Select>
          </WForm.Control>

          <WForm.Control>
            <WForm.UploadSingle name="upload" />
          </WForm.Control>

          <WForm.Control>
            <WForm.ControlLabel
              control={<WForm.Checkbox name="checkbox" />}
              label="Label 1"
            />
          </WForm.Control>

          <WForm.Control>
            <WLocalizationProvider dateAdapter={WAdapterDateFns}>
              <WForm.DatePicker
                name="date"
                label="Date label"
                renderInput={(params, error) => {
                  return (
                    <WTextField
                      {...params}
                      fullWidth
                      margin="normal"
                      error={!!error}
                      helperText={error?.message}
                    />
                  );
                }}
              />
            </WLocalizationProvider>
          </WForm.Control>
        </WBox>
      </WForm>

      <WForm.Control>
        <WForm.Submit form="form">Save</WForm.Submit>
      </WForm.Control>
    </WBox>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = TemplateForm.bind({});
