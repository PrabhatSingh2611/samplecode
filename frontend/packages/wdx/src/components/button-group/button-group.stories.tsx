import { Meta, Story } from '@storybook/react';
import React from 'react';
import WButtonGroup from './index';
import WButton from '../button';

const meta: Meta = {
  title: 'MUI/Inputs/ButtonGroup',
  component: WButtonGroup,
  argTypes: {
    children: {
      control: {
        type: 'contained',
      },
    },
  },
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return (
    <WButtonGroup {...args} aria-label="outlined primary button group" sx={{mx: 'auto'}}>
      <WButton>One</WButton>
      <WButton>Two</WButton>
      <WButton>Three</WButton>
    </WButtonGroup>
  );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  size: "medium",
  color: "primary",
  variant: "contained",
  orientation: "horizontal",
  fullWidth: true,
  disabled: false,
};
