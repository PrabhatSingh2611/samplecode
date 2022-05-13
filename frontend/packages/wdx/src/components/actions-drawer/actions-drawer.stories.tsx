import { Meta, Story } from '@storybook/react';
import React, { useState } from 'react';
import WActionsDrawer from './index';
import WButton from '../button';
import WTypography from '../typography';

const meta: Meta = {
  title: 'Extra Components/ActionsDrawer',
  component: WActionsDrawer,
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
      <WButton onClick={() => setIsDrawerOpened(true)}>
        Open actions drawer
      </WButton>

      <WActionsDrawer
        {...args}
        isOpened={isDrawerOpened}
        onClose={() => setIsDrawerOpened(false)}
        title="Settings"
      >
        <WTypography>{'Drawer content'}</WTypography>
      </WActionsDrawer>
    </>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {};
