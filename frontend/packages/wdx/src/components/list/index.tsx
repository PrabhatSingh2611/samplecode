import React from "react";

import List, { ListProps } from '@mui/material/List';
import ListItem, { ListItemProps } from '@mui/material/ListItem';
import ListDatails, { ListItemAvatarProps } from '@mui/material/ListItemAvatar';
import ListItemButton, { ListItemButtonProps } from '@mui/material/ListItemButton';
import ListItemIcon, { ListItemIconProps } from '@mui/material/ListItemIcon';
import ListItemSecondaryAction, { ListItemSecondaryActionProps } from '@mui/material/ListItemSecondaryAction';
import ListItemText, { ListItemTextProps } from '@mui/material/ListItemText';
import ListSubheader, { ListSubheaderProps } from '@mui/material/ListSubheader';

export interface WListProps extends ListProps {}
export interface WListItemProps extends ListItemProps {}
export interface WListItemAvatarProps extends ListItemAvatarProps {}
export interface WListItemButtonProps extends ListItemButtonProps {}
export interface WListItemIconProps extends ListItemIconProps {}
export interface WListItemSecondaryActionProps extends ListItemSecondaryActionProps {}
export interface WListItemTextProps extends ListItemTextProps {}
export interface WListSubheaderProps extends ListSubheaderProps {}


WList.Item = function(props: WListItemProps):JSX.Element {
    return <ListItem {...props} />;
}

WList.ItemAvatar = function(props: WListItemAvatarProps):JSX.Element {
    return <ListDatails {...props} />;
}

WList.ItemButton = function(props: WListItemButtonProps):JSX.Element {
    return <ListItemButton {...props} />;
}

WList.ItemIcon = function(props: WListItemIconProps):JSX.Element {
    return <ListItemIcon {...props} />;
}

WList.ItemSecondaryAction = function(props: WListItemSecondaryActionProps):JSX.Element {
    return <ListItemSecondaryAction {...props} />;
}

WList.ItemText = function(props: WListItemTextProps):JSX.Element {
    return <ListItemText {...props} />;
}

WList.Subheader = function(props: WListSubheaderProps):JSX.Element {
    return <ListSubheader {...props} />;
}

function WList(props: WListProps):JSX.Element {
    return <List {...props} />;
}

export default WList;