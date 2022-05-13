import { Meta, Story } from '@storybook/react';
import React, { useState } from 'react';
import WDrawer from './index';
import WButton from '../button';
import WBox from '../box';
import WTypography from '../typography';

const meta: Meta = {
  title: 'MUI/Inputs/Drawer',
  component: WDrawer,
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
  const [isDrawerOpened, setIsDrawerOpened] = useState(false);
  return (
    <>
      <WButton onClick={() => setIsDrawerOpened(true)}>Open drawer</WButton>
      <WDrawer {...args} open={isDrawerOpened} onClose={() => setIsDrawerOpened(false)}>
        <WBox p={5}>
          <WTypography>{'Drawer content'}</WTypography>
        </WBox>
      </WDrawer>
    </>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  anchor: 'right',
};
