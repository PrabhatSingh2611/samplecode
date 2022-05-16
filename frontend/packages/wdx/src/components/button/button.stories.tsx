import { Meta, Story } from '@storybook/react';
import React from 'react';
import WButton from './index';

const meta: Meta = {
  title: 'MUI/Inputs/Button',
  component: WButton,
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
  return <WButton {...args} />;
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  size: "medium",
  color: "primary",
  variant: "text",
  fullWidth: false,
  disabled: false,
  children: "click me",
};
