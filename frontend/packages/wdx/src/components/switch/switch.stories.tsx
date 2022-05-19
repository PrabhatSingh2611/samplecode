import { Meta, Story } from '@storybook/react';
import React from 'react';
import WSwitch from '.';
import WBox from '../box';
import WForm from '../form';
import WTypography from '../typography';

const meta: Meta = {
  title: 'MUI/Inputs/Switch',
  component: WSwitch,
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
        <WSwitch {...args} color="default" />
        <WSwitch {...args} color="error" />
        <WSwitch {...args} color="info" />
        <WSwitch {...args} color="primary" />
        <WSwitch {...args} color="success" />
      </WBox>
      <WTypography variant="h3">Sizes</WTypography>
      <WBox display="flex" gap={4} alignItems="center">
        <WSwitch {...args} size="small" />
        <WSwitch {...args} />
      </WBox>
    </WBox>
  );
};

const TemplateGroup: Story = (args) => {
  return (
    <WForm.Group {...args}>
      <WForm.ControlLabel control={<WSwitch />} label="Label 1" />
      <WForm.ControlLabel control={<WSwitch />} label="Label 2" />
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
