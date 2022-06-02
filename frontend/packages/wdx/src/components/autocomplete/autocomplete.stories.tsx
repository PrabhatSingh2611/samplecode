import * as React from 'react';
import WAutocomplete from './index';
import { Meta, Story } from '@storybook/react';
import WTextField from '../text-field';
import { getRandomCountry } from '../../utils/getRandomEntity';

const meta: Meta = {
  title: 'MUI/Inputs/Autocomplete',
  component: WAutocomplete,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = () => {
  return (
    <WAutocomplete
      id="free-solo-demo"
      freeSolo
      options={countries.map((option) => option.label)}
      renderInput={(params) => <WTextField {...params} label="freeSolo" />}
    />
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

interface CountryType {
  label: string;
}

const countries: readonly CountryType[] = [
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
  { label: getRandomCountry() },
];
