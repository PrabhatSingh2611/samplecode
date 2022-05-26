import React from 'react';
import { Theme } from '@mui/material';
import { Meta, Story } from '@storybook/react';
import WBox from '../box';
import WSkeleton from './index';

const meta: Meta = {
  title: 'MUI/Feedback/Skeleton',
  component: WSkeleton,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return (
    <WBox width="90vw" height="90vh" display="flex" alignItems="center" justifyContent="center">
        <WSkeleton {...args} />
    </WBox>
  )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  variant: 'text',
  animation: 'pulse',
  width: 400,
  height: 400,
};
