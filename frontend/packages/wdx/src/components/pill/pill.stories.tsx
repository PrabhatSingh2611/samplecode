import { Meta, Story } from '@storybook/react';
import React from 'react';
import WPill from '.';

const meta: Meta = {
  title: 'MUI/Data Display/Pill',
  component: WPill,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return (
        <WPill {...args}>Label</WPill>
  );
}


export const Default = Template.bind({});

Default.args = {
    variant: 'ghost',
    color: 'primary',
}
