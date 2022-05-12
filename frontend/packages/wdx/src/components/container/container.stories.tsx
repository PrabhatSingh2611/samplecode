import { Theme } from '@mui/material';
import { Meta, Story } from '@storybook/react';
import React from 'react';
import WContainer from './index';

const meta: Meta = {
  title: 'MUI/Layout/Container',
  component: WContainer,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return <WContainer {...args}>
      I'm {args.component}
      <div>max-width: {args.maxWidth}</div>
      <div>Am i fixed: {args.fixed ? 'YES' : 'NO'}</div>
  </WContainer>;
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  component: 'div',
  maxWidth: 'sm',
  fixed: true,
  sx: (theme: Theme) => ({
      display: 'grid',
      placeContent: 'center',
      height: 200,
      background: theme.palette.info.main,
      color: theme.palette.common.white
  })
};
