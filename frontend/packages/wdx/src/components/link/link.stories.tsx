import { Meta, Story } from '@storybook/react';
import React from 'react';
import WLink from './index';
import WStack from '../stack';

const meta: Meta = {
  title: 'MUI/Navigation/Link',
  component: WLink,
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

const Template: Story = (args) => {
  return (
    <WStack direction="row" spacing={2} justifyContent="center" alignItems="flex-end">
      <WLink {...args} href="#">Link</WLink>
      <WLink {...args} href="#" color="inherit">
        {'color="inherit"'}
      </WLink>
      <WLink {...args} href="#" variant="body2">
        {'variant="body2"'}
      </WLink>
    </WStack>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  variant: 'inherit',
  underline: 'none',
  component: 'a',
  color: 'primary'
};
