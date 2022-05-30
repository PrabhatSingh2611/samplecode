import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBreadcrumbs from '../../components/breadcrumbs';
import WButton from '../../components/button';
import WLink from '../../components/link';
import WTypography from '../../components/typography';
import WSubHeader from './index';

const meta: Meta = {
  title: 'Widgets/SubHeader',
  component: WSubHeader,
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
  return (
    <WSubHeader
      {...args}
      subtitle="I'm subtitle"
      actions={
        <>
          <WButton color="secondary" size="large">Secondary</WButton>
          <WButton variant="outlined" color="error" size="large">Error</WButton>
          <WButton variant="contained" size="large">Success</WButton>
        </>
      }
    />
  );
};
const TemplateWithBreadcrumbs: Story = (args) => {
  return (
    <WSubHeader
      {...args}
      breadcrumbs={
          <WBreadcrumbs aria-label="breadcrumb">
            <WLink underline="hover" color="inherit" href="/">
                MUI
            </WLink>
            <WLink
                underline="hover"
                color="inherit"
                href="/material-ui/getting-started/installation/"
            >
                Core
            </WLink>
            <WTypography color="text.primary">Breadcrumbs</WTypography>
        </WBreadcrumbs>
      }
      actions={
        <>
          <WButton color="secondary" size="large">Secondary</WButton>
          <WButton variant="outlined" color="error" size="large">Error</WButton>
          <WButton variant="contained" size="large">Success</WButton>
        </>
      }
    />
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
export const WithBreadcrumbs = TemplateWithBreadcrumbs.bind({});

Default.args = {title: 'Info'}
WithBreadcrumbs.args = {title: 'Info'}
