import { Meta, Story } from '@storybook/react';
import React from 'react';
import WToggleButtonGroup, { WToggleButton } from './index';

const meta: Meta = {
  title: 'MUI/Inputs/ToggleButton',
  component: WToggleButton,
  argTypes: {
    children: {
      control: {
        type: 'contained',
      },
    },
  },
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = (args) => {
  const [alignment, setAlignment] = React.useState<string | null>('left');
  const handleAlignment = (
    _event: React.MouseEvent<HTMLElement>,
    newAlignment: string | null,
  ): void => {
    setAlignment(newAlignment);
  };

  return (
    <WToggleButtonGroup {...args} value={alignment} onChange={handleAlignment} aria-label="text alignment">
      <WToggleButton value={1}>One</WToggleButton>
      <WToggleButton value={2}>Two</WToggleButton>
      <WToggleButton value={3} disabled={true}>Three (disabled)</WToggleButton>
    </WToggleButtonGroup>
  );
}

const TemplateStandalone: Story = (args) => {
  const [selected, setSelected] = React.useState(false);

  return (
    <WToggleButton
      {...args}
      value="check"
      selected={selected}
      onChange={() => {
        setSelected(!selected);
      }}
    >
      Check
    </WToggleButton>
  );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
export const Standalone = TemplateStandalone.bind({});

Default.args = {
  size: "medium",
  color: "primary",
  orientation: "horizontal",
  exclusive: false,
  fullWidth: true,
};

Standalone.args = {
  size: "medium",
  color: "primary",
  disabled: false,
  fullWidth: true,
};
