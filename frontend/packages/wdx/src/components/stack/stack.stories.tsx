import { Theme } from '@mui/material';
import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import WStack from './index';

const meta: Meta = {
  title: 'MUI/Layout/Stack',
  component: WStack,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const StackItem = () => {
    return (
        <WBox
            sx={(theme: Theme) => ({
                border: `1px solid ${theme.palette.divider}`,
                backgroundColor: theme.palette.background.default,
                textAlign: 'center',
            })}
        >
            I'm a stack item
        </WBox>
    )
}

const Template: Story = args => {
  return (
    <WStack {...args}>
        <StackItem />
        <StackItem />
        <StackItem />
    </WStack>

  )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
    component: 'section',
    direction: 'column',
    wrap: 'wrap',
    divider: <WBox sx={theme => ({height: '1px', backgroundColor: theme.palette.primary.lighter})}/>,
    spacing: 2,
    sx: (theme: Theme) => ({
        p: 2,
        background: theme.palette.info.main,
    })
};
