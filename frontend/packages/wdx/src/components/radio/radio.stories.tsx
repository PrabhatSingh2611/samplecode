import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import WFormControl, { WFormControlLabel } from '../form';
import WRadio, { WRadioGroup } from './index';

const meta: Meta = {
  title: 'MUI/Inputs/Radio',
  component: WRadio,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return (
    <WBox display='flex' gap={4}>
        <WRadio {...args} color="default" />
        <WRadio {...args} color="error" />
        <WRadio {...args} color="info" />
        <WRadio {...args} color="primary" />
        <WRadio {...args} color="success" />
    </WBox>

  )
}

const TemplateGroup: Story = args => {
    return (
        <WFormControl>
            <WRadioGroup
                {...args}
                aria-labelledby="demo-row-radio-buttons-group-label"
                name="row-radio-buttons-group"
            >
                <WFormControlLabel value="female" control={<WRadio />} label="Female" />
                <WFormControlLabel value="male" control={<WRadio />} label="Male" />
            </WRadioGroup>
        </WFormControl>

    )
  }

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
export const Group = TemplateGroup.bind({});

Default.args = {
  checked: false,
  disabled: false,
  disableRipple: false,
};

Group.args = {
    row: true,
}
