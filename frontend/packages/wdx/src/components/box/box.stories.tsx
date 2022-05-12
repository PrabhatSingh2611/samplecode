import { Theme } from '@mui/material';
import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from './index';

const meta: Meta = {
  title: 'MUI/Layout/Box',
  component: WBox,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return <WBox {...args}>I'm {args.component}</WBox>;
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  component: 'section',
  sx: (theme: Theme) => ({
      display: 'grid',
      placeContent: 'center',
      width: 200,
      height: 200,
      background: theme.palette.info.main,
      color: theme.palette.common.white
  })
};
