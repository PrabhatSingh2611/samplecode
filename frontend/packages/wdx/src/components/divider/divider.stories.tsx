import { Meta, Story } from '@storybook/react';
import React from 'react';
import WDivider from './index';
import WTypography from '../typography';

const meta: Meta = {
  title: 'MUI/Data Display/Divider',
  component: WDivider,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = (args) => {
  return (
    <>
      <WTypography>simple text</WTypography>
      <WDivider {...args} />
      <WTypography>another simple text</WTypography>
    </>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {};
