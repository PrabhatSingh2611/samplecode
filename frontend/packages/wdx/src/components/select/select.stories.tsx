import { Meta, Story } from '@storybook/react';
import React from 'react';
import WSelect from './index';
import { WMenuItem } from '../menu';

const meta: Meta = {
  title: 'MUI/Inputs/Select',
  component: WSelect,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = (args) => {
  return (
    <WSelect {...args} sx={{ width: 200 }}>
      <WMenuItem value={10}>Ten</WMenuItem>
      <WMenuItem value={20}>Twenty</WMenuItem>
      <WMenuItem value={30}>Thirty</WMenuItem>
    </WSelect>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {};
