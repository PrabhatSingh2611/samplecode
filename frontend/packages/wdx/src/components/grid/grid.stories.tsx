import { Theme } from '@mui/material';
import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import WGrid from './index';

const meta: Meta = {
  title: 'MUI/Layout/Grid',
  component: WGrid,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const GridItem = () => {
    return (
        <WGrid item xs={4}>
            <WBox
                sx={(theme: Theme) => ({
                    p: 4,
                    boxShadow: theme.shadows[12],
                    border: `1px solid ${theme.palette.divider}`,
                    backgroundColor: theme.palette.background.default,
                })}
            >
               I'm a grid 4 colums item
            </WBox>
        </WGrid>
    )
}

const Template: Story = args => {
  return (
    <WGrid {...args}>
        <GridItem />
        <GridItem />
        <GridItem />
        <GridItem />
    </WGrid>
  )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  component: 'section',
  container: true,
  direction: 'row',
  justifyContent: 'flex-start',
  alignItems: 'center',
  wrap: 'wrap',
  spacing: 3,
  sx: (theme: Theme) => ({
      p: 2,
      minHeight: 200,
      background: theme.palette.info.main,
  })
};
