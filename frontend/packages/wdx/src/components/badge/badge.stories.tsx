import { Meta, Story } from '@storybook/react';
import React from 'react';
import WButton from '../button';
import WBadge from './index';

const meta: Meta = {
  title: 'MUI/Data Display/Badge',
  component: WBadge,
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
  return <WBadge {...args}><WButton variant='contained'>Toggle popover</WButton></WBadge>;
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  badgeContent: 4,
  color: 'error'
};
