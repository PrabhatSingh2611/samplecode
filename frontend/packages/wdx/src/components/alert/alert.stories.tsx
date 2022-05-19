import { Meta, Story } from '@storybook/react';
import React from 'react';
import WButton from '../button';
import WStack from '../stack';
import WAlert, { WAlertTitle } from './index';

const meta: Meta = {
  title: 'MUI/Feedback/Alert',
  component: WAlert,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = () => {
  return (
        <WStack sx={{ width: '100%' }} spacing={2}>
            <WAlert severity="error">
                <WAlertTitle>Error</WAlertTitle>
                This is an error alert
            </WAlert>
            <WAlert severity="warning">
                <WAlertTitle>Warning</WAlertTitle>
                This is a warning alert
            </WAlert>
            <WAlert severity="info">
                <WAlertTitle>Info</WAlertTitle>
                This is an info alert
            </WAlert>
            <WAlert severity="success">
                <WAlertTitle>Success</WAlertTitle>
                This is a success alert
            </WAlert>
        </WStack>
  );
}

const TemplateOutlined: Story = () => {
    return (
        <WStack sx={{ width: '100%' }} spacing={2}>
            <WAlert variant="outlined" severity="error">
                This is an error alert
            </WAlert>
            <WAlert variant="outlined" severity="warning">
                This is a warning alert
            </WAlert>
            <WAlert
                variant="outlined"
                severity="info"
                action={<WButton>Action</WButton>}
            >
                This is an info alert width action
            </WAlert>
            <WAlert variant="outlined" severity="success">
                This is a success alert
            </WAlert>
        </WStack>
    );
}

export const Default = Template.bind({});
export const Outlined = TemplateOutlined.bind({});
