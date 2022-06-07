import { Meta, Story } from '@storybook/react';
import React, { useState } from 'react';
import WButton from '../button';
import { WCircularProgress } from '../progress';
import WStack from '../stack';
import WBackdrop from './index';

const meta: Meta = {
    title: 'MUI/Feedback/Backdrop',
    component: WBackdrop,
    parameters: {
        controls: { expanded: true },
    },
};

export default meta;

const Template: Story = () => {
    const [open, setOpen] = useState(false);
    const handleClose = () => {
        setOpen(false);
    };
    const handleToggle = () => {
        setOpen(!open);
    };

    return (
        <WStack >
            <WButton variant="contained" onClick={handleToggle}>Show backdrop</WButton>
            <WBackdrop
                sx={{ color: (theme) => theme.palette.grey[0], zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={open}
                onClick={handleClose}
            >
                <WCircularProgress color="inherit" />
            </WBackdrop>
        </WStack>
    );
}


export const Default = Template.bind({});
