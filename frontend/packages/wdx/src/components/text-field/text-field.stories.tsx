import { Meta, Story } from '@storybook/react';
import React from 'react';
import { WSelect } from '../..';
import WBox from '../box';
import { WMenuItem } from '../menu';
import WSelect from '../select';
import WTypography from '../typography';
import WTextField from './index';

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
    return <WTextField {...args} multiline />;
}

const TemplateSelect: Story = args => {
    const [value, setValue] = React.useState('label1');
    return (
        <WBox display="flex" gap={4}>
            <WBox>
                <WTypography>Select builded with TextField component</WTypography>
                <WTextField fullWidth {...args} value={value} onChange={(e) => setValue(e.target.value)} select>
                    {selectData.map((option) => (
                        <WMenuItem key={option.value} value={option.value}>
                            {option.label}
                        </WMenuItem>
                    ))}
                </WTextField>
            </WBox>
            <WBox>
                <WTypography>Select builded with Select component</WTypography>
                <WSelect fullWidth onChange={(e) => setValue(e.target.name)}>
                    {selectData.map((option) => (
                        <WMenuItem key={option.value} value={option.value}>
                            {option.label}
                        </WMenuItem>
                    ))}
                </WSelect>
            </WBox>
        </WBox>
    )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
export const TextArea = TemplateTextArea.bind({});
export const Select = TemplateSelect.bind({});


Default.args = {
    ...defalutArgs,
};

TextArea.args = {
    ...defalutArgs,
    multiline: true,
    maxRows: 4,
}