import { Meta, Story } from '@storybook/react';
import React from 'react';
import WAvatarGroup from './index';
import WAvatar from '../avatar';

const meta: Meta = {
  title: 'MUI/Data Display/Avatar Group',
  component: WAvatarGroup,
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
  return <WAvatarGroup {...args}><WAvatar/><WAvatar/><WAvatar/></WAvatarGroup>;
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  max: 2,
};
