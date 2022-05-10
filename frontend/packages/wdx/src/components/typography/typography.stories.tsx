import { Meta, Story } from '@storybook/react';
import React from 'react';
import WTypography from './index';

const meta: Meta = {
  title: 'Components/Typography',
  component: WTypography,
  argTypes: {
    children: {
      control: {
        type: 'text',
      },
    },
  },
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return <WTypography {...args} />;
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  children: 'this is text'
};
