import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import { WMenuItem } from '../menu';
import WTypography from '../typography';
import WTextField, { WFilledInput, WFormControl, WFormHelperText, WInput, WOutlinedInput } from './index';

const meta: Meta = {
  title: 'MUI/Inputs/TextField',
  component: WTextField,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const defalutArgs = {
    label: 'Text Field',
    placeholder: 'Text Field',
    variant: 'standard',
}

const selectData = [
    {
      value: 'label1',
      label: 'label1',
    },
    {
      value: 'label2',
      label: 'label2',
    },
    {
      value: 'label3',
      label: 'label3',
    },
    {
      value: 'label4',
      label: 'label4',
    },
  ];

const Template: Story = args => {
  return (
    <WBox display="flex" flexWrap="wrap" gap={2}>
        <WBox>
            <WTypography mb={2}>Standard text field</WTypography>
            <WTextField {...args} />
        </WBox>
        <WBox>
            <WTypography mb={2}>Outlined text field</WTypography>
            <WTextField {...args} variant="outlined" />
        </WBox>
        <WBox>
            <WTypography mb={2}> Filled text field</WTypography>
            <WTextField {...args} variant="filled" />
        </WBox>

        <WBox>
            <WTypography mb={2}> Small text field</WTypography>
            <WTextField {...args} variant="outlined" size="small" />
        </WBox>
        <WBox>
            <WTypography mb={2}> Password text field</WTypography>
            <WTextField {...args} variant="standard" type="password" />
        </WBox>
    </WBox>
  )
}

const TemplateTextArea: Story = args => {
    return <WTextField {...args} />;
}

const TemplateSelect: Story = args => {
    const [value, setValue] = React.useState('label1');
    return (
        <WTextField {...args} value={value} onChange={(e) => setValue(e.target.value)} select>
            {selectData.map((option) => (
                <WMenuItem key={option.value} value={option.value}>
                    {option.label}
                </WMenuItem>
            ))}
        </WTextField>
    )
}

const TemplateBaseInputs: Story = () => {
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
export const Default = Template.bind({});
export const TextArea = TemplateTextArea.bind({});
export const Select = TemplateSelect.bind({});
export const BaseInput = TemplateBaseInputs.bind({});


Default.args = {
    ...defalutArgs,
};

TextArea.args = {
    ...defalutArgs,
    multiline: true,
    maxRows: 4,
}