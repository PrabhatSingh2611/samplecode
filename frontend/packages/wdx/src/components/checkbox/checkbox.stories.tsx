import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import WForm from '../form';
import WCheckbox from '../checkbox';
import WTypography from '../typography';

const meta: Meta = {
  title: 'MUI/Inputs/Checkbox',
  component: WCheckbox,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = (args) => {
  return (
    <WBox>
      <WTypography variant="h3">Colors</WTypography>
      <WBox display="flex" gap={4} alignItems="center">
        <WCheckbox {...args} color="default" />
        <WCheckbox {...args} color="error" />
        <WCheckbox {...args} color="info" />
        <WCheckbox {...args} color="primary" />
        <WCheckbox {...args} color="success" />
      </WBox>
      <WTypography variant="h3">Sizes</WTypography>
      <WBox display="flex" gap={4} alignItems="center">
        <WCheckbox {...args} size="small" />
        <WCheckbox {...args} />
        <WCheckbox {...args} sx={{ '& .MuiSvgIcon-root': { fontSize: 36 } }} />
      </WBox>
    </WBox>
  );
};

const TemplateGroup: Story = (args) => {
  return (
    <WForm.Group {...args}>
      <WForm.ControlLabel control={<WCheckbox />} label="Label 1" />
      <WForm.ControlLabel control={<WCheckbox />} label="Label 2" />
    </WForm.Group>
  );
};

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
};
