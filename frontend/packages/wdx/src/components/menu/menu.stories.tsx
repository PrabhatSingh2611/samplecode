import { action } from '@storybook/addon-actions';
import { Meta, Story } from '@storybook/react';
import React from 'react';
import WButton from '../button';
import WMenu, { WMenuItem, WMenuList } from './index';

const meta: Meta = {
    title: 'MUI/Navigation/Menu (Dropdown)',
    component: WMenu,
    parameters: {
        controls: { expanded: true },
    },
};

export default meta;

const TemplateDefault: Story = args => {
    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);
    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <>
            <WButton
                id="basic-button"
                onClick={handleClick}
                aria-controls={open ? 'basic-menu' : undefined}
                aria-haspopup="true"
                aria-expanded={open ? 'true' : undefined}
            >
                Dashboard
            </WButton>
            <WMenu id="basic-menu"
                open={open}
                anchorEl={anchorEl}
                onClose={handleClose}
                MenuListProps={{
                    'aria-labelledby': 'basic-button',
                }}
                {...args}>
                <WMenuItem onClick={action('OnClick')}>Profile</WMenuItem>
                <WMenuItem onClick={action('OnClick')}>My account</WMenuItem>
                <WMenuItem onClick={action('OnClick')}>Logout</WMenuItem>
            </WMenu>
        </>
    )
}

const TemplateMenuList: Story = args => {
    return (
        <WMenuList {...args}>
            <WMenuItem>Profile</WMenuItem>
            <WMenuItem>My account</WMenuItem>
            <WMenuItem>Logout</WMenuItem>
        </WMenuList>
    )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = TemplateDefault.bind({});
export const MenuWithActions = TemplateMenuList.bind({});

