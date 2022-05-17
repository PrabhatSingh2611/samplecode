import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import { WFilledInput, WInput, WOutlinedInput } from '../text-field';
import WFormControl, { WFormHelperText } from './index';

const meta: Meta = {
  title: 'MUI/Inputs/Form',
  component: WBox,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const TemplateForm: Story = () => {
    return (
        <WBox display="flex" flexWrap="wrap" gap={2}>
            <WFormControl>
                <WOutlinedInput placeholder="Please enter text" />
                <WFormHelperText>Some helper text here</WFormHelperText>
            </WFormControl>

            <WFormControl>
                <WInput placeholder="Please enter text" />
                <WFormHelperText>Some helper text here</WFormHelperText>
            </WFormControl>

            <WFormControl>
                <WFilledInput placeholder="Please enter text" />
                <WFormHelperText>Some helper text here</WFormHelperText>
            </WFormControl>
        </WBox>
    )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = TemplateForm.bind({});
