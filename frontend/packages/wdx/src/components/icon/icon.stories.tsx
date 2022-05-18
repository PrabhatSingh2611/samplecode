
import { Meta, Story } from '@storybook/react';
import React from 'react';
import WIcon from './index';

const meta: Meta = {
  title: 'MUI/Data Display/Icon',
  component: WIcon,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return <WIcon {...args} name="close" color='primary'/>;
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
