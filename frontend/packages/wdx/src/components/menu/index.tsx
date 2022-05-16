import React from "react";

import Menu, { MenuProps } from '@mui/material/Menu';
import MenuItem, { MenuItemProps } from '@mui/material/MenuItem';
import MenuList, { MenuListProps } from '@mui/material/MenuList';

export interface WMenuProps extends MenuProps {}

export interface WMenuItemProps extends MenuItemProps {}

export interface WMenuListProps extends MenuListProps {}


export function WMenuItem(props: WMenuItemProps):JSX.Element {
    return <MenuItem {...props} />;
}

export function WMenuList(props: WMenuListProps):JSX.Element {
    return <MenuList {...props} />;
}

function WMenu(props: WMenuProps):JSX.Element {
    return <Menu {...props} />;
}

export default WMenu;