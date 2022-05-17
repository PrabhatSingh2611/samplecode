import { Meta, Story } from '@storybook/react';
import React from 'react';
import WIconify from './index';
import WStack from '../stack';

const meta: Meta = {
  title: 'Extra Components/Iconify',
  component: WIconify,
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
    <WStack direction="row" justifyContent="center" alignItems="center" gap={2}>
      <WIconify {...args} icon="eva:more-vertical-fill" width={20} height={20} />
      <WIconify {...args} icon="eva:activity-outline" width={20} height={20} />
      <WIconify {...args} icon="eva:book-outline" width={20} height={20} />
    </WStack>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
