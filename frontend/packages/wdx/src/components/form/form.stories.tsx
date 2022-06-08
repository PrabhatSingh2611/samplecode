import { Meta, Story } from '@storybook/react';
import React, { HTMLAttributes } from 'react';
import WBox from '../box';
import * as Yup from 'yup';
import WForm from './index';
import { WMenuItem } from '../menu';
import { WAdapterDateFns, WLocalizationProvider } from '../date-picker';
import WTextField from '../text-field';
import { getRandomCountry } from '../../utils/getRandomEntity';
import WList from '../list';
import { castToString } from '../../utils/maybeUtils';

const meta: Meta = {
  title: 'MUI/Inputs/Form',
  component: WForm,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

interface ICountryType {
  label: string;
}

const countries: ICountryType[] = [
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
];

const TemplateForm: Story = () => {
  const validation = Yup.object().shape({
    input: Yup.string().required('Name is required'),
    animal: Yup.string().required('Animal is required'),
    upload: Yup.object().required('Upload is required'),
    date: Yup.date().required('Date is required'),
    autocomplete: Yup.lazy((value) => {
      switch (typeof value) {
        case 'object':
          return Yup.object({
            label: Yup.string().required('Autocomplete is required'),
          });
        default:
          return Yup.string().min(2);
      }
    }),
  });

  const defaultValues = {
    input: 'initial value',
    date: null,
    autocomplete: '',
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
          <WForm.Control component="fieldset">
            <WForm.Label component="legend" variant="bold">
              Label
            </WForm.Label>
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
          <WForm.Control>
            <WForm.Autocomplete
              name="autocomplete"
              freeSolo={true}
              options={countries}
              getOptionLabel={(option: any) =>
                castToString(option?.label || option)
              }
              renderOption={(
                props: HTMLAttributes<HTMLLIElement>,
                option: any
              ): JSX.Element => (
                <WList.Item {...props}>{option.label || option}</WList.Item>
              )}
              renderInput={(params, error) => (
                <WTextField
                  {...params}
                  label="Choose a country"
                  variant="outlined"
                  error={!!error}
                  helperText={error?.message}
                />
              )}
            />
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
