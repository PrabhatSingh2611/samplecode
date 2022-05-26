import { Meta, Story } from '@storybook/react';
import React, { useState } from 'react';
import WBox from '../box';
import WButton from '../button';
import WSkeleton from '../skeleton';
import WDialog from './index';

const meta: Meta = {
  title: 'MUI/Feedback/Dialog',
  component: WDialog,
  parameters: {
    controls: { expanded: true },
  },
  args: {
      fullWidth: true,
  }
};

export default meta;

const Template: Story = args => {
    const [open, setOpen] = useState(false);

    return (
        <WBox>
            <WButton onClick={()=> setOpen(true)}>Open Dialog</WButton>
            <WDialog {...args} open={open} onClose={() => setOpen(false)}>
                <WDialog.Content>
                    <WSkeleton  width="100%" height={100} />
                </WDialog.Content>
            </WDialog>
        </WBox>
    )
}

const TemplateWithActions: Story = args => {
    const [open, setOpen] = useState(false);

    return (
        <WBox>
            <WButton onClick={()=> setOpen(true)}>Open Dialog</WButton>
            <WDialog {...args} open={open} onClose={() => setOpen(false)}>
                <WDialog.Content>
                    <WSkeleton  width="100%" height={100} />
                </WDialog.Content>
                <WDialog.Actions>
                    <WButton onClick={()=> setOpen(false)}>Close Dialog</WButton>
                </WDialog.Actions>
            </WDialog>
        </WBox>
    )
}

const TemplateWithText: Story = args => {
    const [open, setOpen] = useState(false);

    return (
        <WBox>
            <WButton onClick={()=> setOpen(true)}>Open Dialog</WButton>
            <WDialog {...args} open={open} onClose={() => setOpen(false)}>
                <WDialog.Content>
                    <WSkeleton  width="100%" height={100} />
                    <WDialog.ContentText>
                        I'm a text inside modal
                    </WDialog.ContentText>
                </WDialog.Content>
                <WDialog.Actions>
                    <WButton onClick={()=> setOpen(false)}>Close Dialog</WButton>
                </WDialog.Actions>
            </WDialog>
        </WBox>
    )
}

const TemplateWithTitle: Story = args => {
    const [open, setOpen] = useState(false);

    return (
        <WBox>
            <WButton onClick={()=> setOpen(true)}>Open Dialog</WButton>
            <WDialog {...args} open={open} onClose={() => setOpen(false)}>
                <WDialog.Title>I'm a dialog title</WDialog.Title>
                <WDialog.Content>
                    <WSkeleton  width="100%" height={100} />
                    <WDialog.ContentText>
                        I'm a text inside modal
                    </WDialog.ContentText>
                </WDialog.Content>
                <WDialog.Actions>
                    <WButton onClick={()=> setOpen(false)}>Close Dialog</WButton>
                </WDialog.Actions>
            </WDialog>
        </WBox>
    )
}

export const Default = Template.bind({});
export const WithActions = TemplateWithActions.bind({});
export const WithText = TemplateWithText.bind({});
export const WithTitle = TemplateWithTitle.bind({});