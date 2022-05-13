import { Meta, Story } from '@storybook/react';
import React from 'react';
import WIconButton from './index';
import WIconify from '../iconify';

const meta: Meta = {
  title: 'MUI/Inputs/IconButton',
  component: WIconButton,
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
    <WIconButton {...args}>
      <WIconify icon={'eva:close-fill'} width={20} height={20} />
    </WIconButton>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {};
