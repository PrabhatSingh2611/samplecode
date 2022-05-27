
import { Meta, Story } from '@storybook/react';
import React from 'react';
import WStack from '../stack';
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
  return (
    <WStack justifyContent="center" gap={2} alignItems="center" direction="row" sx={{ p: 3 }}>
      <WIcon name="settings" {...args}/>
      <WIcon name="settings" {...args} fontSize="small"/>
      <WIcon name="settings" {...args} fontSize="medium"/>
      <WIcon name="settings" {...args} fontSize="large"/>
    </WStack>
  )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  color: "inherit",
  fontSize: "inherit",
}
