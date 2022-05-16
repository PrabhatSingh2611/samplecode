
import { Meta, Story } from '@storybook/react';
import React from 'react';
import WTypography from '../typography';
import WPaper from './index';

const meta: Meta = {
    title: 'MUI/Surfaces/Paper',
    component: WPaper,
    parameters: {
        controls: { expanded: true },
    },
};

export default meta;

const TemplateDefault: Story = args => {
    return (
        <WPaper {...args}>
            <WTypography variant="body2" color="text.secondary">
                PaperData
            </WTypography>
        </WPaper>
    )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = TemplateDefault.bind({});

Default.args = {
    square: false,
    elevation: 4,
    variant: 'elevation',
    sx: { p: 3}
};
