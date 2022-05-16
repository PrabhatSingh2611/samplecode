import { Meta, Story } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import React from 'react';
import WChip from './index';

const meta: Meta = {
  title: 'MUI/Data Display/Chip',
  component: WChip,
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
  return <WChip {...args}/>;
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  label: 'Chip Filled',
  variant: 'circular',
  color: 'primary',
  size: 'medium',
  disabled: false,
  clickable: false,
  onDelete: action('OnClick'),
};
