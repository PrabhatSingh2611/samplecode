import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../../components/box';
import WRating from './index';

const meta: Meta = {
  title: 'MUI/Inputs/Rating',
  component: WRating,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return (
    <WBox display="flex" justifyContent="center" p={3}>
        <WRating {...args} getLabelText={value => `${value} points`} />
    </WBox>
  )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  defaultValue: 3,
  size: 'large',
  precision: 0.2,
  readonly: false,
  max: 6
};
