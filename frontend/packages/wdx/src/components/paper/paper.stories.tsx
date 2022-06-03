import React from 'react';
import { Meta, Story } from '@storybook/react';
import WPaper from './index';
import WBox from '../box';

const meta: Meta = {
  title: 'MUI/Surfaces/Paper',
  component: WPaper,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = (args) => {
  return (
    <WBox {...args}>
      <WPaper elevation={0} variant="outlined" />
      <WPaper variant="outlined" />
      <WPaper elevation={3} variant="outlined" />
    </WBox>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  sx: () => ({
    display: 'flex',
    flexWrap: 'wrap',
    '& > :not(style)': {
      m: 1,
      width: 128,
      height: 128,
    },
  }),
};
