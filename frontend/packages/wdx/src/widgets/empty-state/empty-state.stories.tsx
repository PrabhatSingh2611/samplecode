import { Meta, Story } from '@storybook/react';
import React from 'react';
import WButton from '../../components/button';
import WEmptyState from './index';

const meta: Meta = {
  title: 'Widgets/EmptyState',
  component: WEmptyState,
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
    <WEmptyState
      {...args}
      actions={
        <>
          <WButton variant="outlined" color="error" size="large">Error</WButton>
          <WButton variant="contained" size="large">Create</WButton>
        </>
      }
    />
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  title: 'You have no documents',
  subtitle: 'Use the “Create” button to create the policy',
}
