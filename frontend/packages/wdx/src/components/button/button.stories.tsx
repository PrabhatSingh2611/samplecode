import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import WIcon from '../icon';
import WSwitch from '../switch';
import WButton, { WButtonBase, WIconButton, WLoadingButton } from './index';

const meta: Meta = {
  title: 'MUI/Inputs/Button',
  component: WButton,
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

const Template: Story = args => {
  return <WButton {...args} />;
}

const TemplateBase: Story = args => {
  return <WButtonBase {...args} >Base</WButtonBase>;
}

const TemplateIconButton: Story = args => {
  return (
    <WIconButton {...args}>
      <WIcon {...args} name="close" />
    </WIconButton>
  );
}

const TemplateLoadingButton: Story = args => {
  const [loading, setLoading] = React.useState(true);
  function handleClick() {
    setLoading(true);
  }

  return (
    <WBox>
        <WSwitch
        checked={loading}
        onChange={() => setLoading(!loading)}
        name="loading"
        color="primary"
        />

      <WBox sx={{ '& > button': { m: 1 } }}>
        <WLoadingButton
          size="small"
          onClick={handleClick}
          loading={loading}
          variant="outlined"
          disabled
        >
          disabled
        </WLoadingButton>
        <WLoadingButton
          size="small"
          onClick={handleClick}
          loading={loading}
          loadingIndicator="Loading..."
          variant="outlined"
        >
          Fetch data
        </WLoadingButton>
        <WLoadingButton
          size="small"
          onClick={handleClick}
          endIcon={<WIcon {...args} name="close" />}
          loading={loading}
          loadingPosition="end"
          variant="contained"
        >
          Send
        </WLoadingButton>
        <WLoadingButton
          size="small"
          color="secondary"
          onClick={handleClick}
          loading={loading}
          loadingPosition="start"
          startIcon={<WIcon {...args} name="close" />}
          variant="contained"
        >
          Save
        </WLoadingButton>
      </WBox>

      <WBox sx={{ '& > button': { m: 1 } }}>
        <WLoadingButton
          onClick={handleClick}
          loading={loading}
          variant="outlined"
          disabled
        >
          disabled
        </WLoadingButton>
        <WLoadingButton
          onClick={handleClick}
          loading={loading}
          loadingIndicator="Loading..."
          variant="outlined"
        >
          Fetch data
        </WLoadingButton>
        <WLoadingButton
          onClick={handleClick}
          endIcon={<WIcon {...args} name="close" />}
          loading={loading}
          loadingPosition="end"
          variant="contained"
        >
          Send
        </WLoadingButton>
        <WLoadingButton
          color="secondary"
          onClick={handleClick}
          loading={loading}
          loadingPosition="start"
          startIcon={<WIcon {...args} name="close" />}
          variant="contained"
        >
          Save
        </WLoadingButton>
      </WBox>
    </WBox>
  );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
export const Base = TemplateBase.bind({});
export const IconButton = TemplateIconButton.bind({});
export const LoadingButton = TemplateLoadingButton.bind({});

Default.args = {
  size: "medium",
  color: "primary",
  variant: "text",
  fullWidth: false,
  disabled: false,
  children: "click me",
};

IconButton.args = {
  size: "medium",
  color: "primary",
  disabled: false,
};
