import { Meta, Story } from '@storybook/react';
import React from 'react';
import { StarIcon } from '../../theme/overrides/CustomIcons';
import WStack from '../stack';
import WFab from './index';

const meta: Meta = {
  title: 'MUI/Inputs/Fab',
  component: WFab,
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
      <WFab {...args} />
      <WFab {...args} color="primary" aria-label="add">
        <StarIcon />
      </WFab>
      <WFab {...args} color="secondary" aria-label="edit">
        <StarIcon />
      </WFab>
      <WFab {...args} variant="extended">
        <StarIcon sx={{ mr: 1 }} />
        Navigate
      </WFab>
      <WFab {...args} disabled aria-label="like">
        <StarIcon />
      </WFab>
    </WStack>
  );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  size: "medium",
  color: "primary",
  variant: "text",
  disabled: false,
  children: "FAB",
};
