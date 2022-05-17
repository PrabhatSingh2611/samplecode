import { Meta, Story } from '@storybook/react';
import React from 'react';
import WTooltip from './index';
import WButton from '../button';
import { Fade, Zoom } from '@mui/material';
import WStack from '../stack';

const meta: Meta = {
  title: 'MUI/Data Display/Tooltip',
  component: WTooltip,
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
  return (
    <WStack
      direction="row"
      justifyContent="center"
      alignItems="center"
      spacing={2}
    >
      <WTooltip {...args} title='Tooltip Filled'><WButton variant='contained'>Tooltip</WButton></WTooltip>
    </WStack>);
}

const TemplateTransitions: Story = args => {

  return (
    <WStack
      direction="row"
      justifyContent="center"
      alignItems="center"
      spacing={2}
    >
      <WTooltip {...args} title="Add">
        <WButton>Grow</WButton>
      </WTooltip>
      <WTooltip
        {...args}
        TransitionComponent={Fade}
        TransitionProps={{ timeout: 600 }}
        title="Add"
      >
        <WButton>Fade</WButton>
      </WTooltip>
      <WTooltip {...args} TransitionComponent={Zoom} title="Add">
        <WButton>Zoom</WButton>
      </WTooltip>
    </WStack>
  );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
export const Transitions = TemplateTransitions.bind({});

Default.args = {
  title: 'Tooltip Filled',
  placement: 'bottom',
  arrow: false,
  disableInteractive: false,
  disableFocusListener: false,
  disableHoverListener: false,
  enterDelay: 200,

};
Transitions.args = {
  placement: 'top',
};
