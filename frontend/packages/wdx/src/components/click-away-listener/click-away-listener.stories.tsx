import { SxProps } from '@mui/material';
import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import WClickAwayListener from './index';

const meta: Meta = {
    title: 'MUI/Utils/ClickAwayListener',
    component: WClickAwayListener,
    parameters: {
        controls: { expanded: true },
    },
};

export default meta;

const TemplateDefault: Story = (args) => {
    const [open, setOpen] = React.useState(false);

    const handleClick = () => {
        setOpen((prev) => !prev);
    };

    const handleClickAway = () => {
        setOpen(false);
    };

    const styles: SxProps = {
        position: 'absolute',
        top: 28,
        right: 0,
        left: 0,
        zIndex: 1,
        border: '1px solid',
        p: 1,
        bgcolor: 'background.paper',
    };

    return (
        <WClickAwayListener {...args} onClickAway={handleClickAway}>
            <WBox sx={{ position: 'relative' }}>
                <button type="button" onClick={handleClick}>
                    Open menu dropdown
                </button>
                {open ? (
                    <WBox sx={styles}>
                        Click me, I will stay visible until you click outside.
                    </WBox>
                ) : null}
            </WBox>
        </WClickAwayListener>
    );
}


// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const ClickAwayListener = TemplateDefault.bind({});