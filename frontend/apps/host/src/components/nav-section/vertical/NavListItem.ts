import { ElementType } from 'react';

import {
    LinkProps,
    ListItemText,
    ListItemButton,
    ListItemIcon,
    ListItemButtonProps,
} from '@mui/material';
import { alpha, styled } from '@mui/material/styles';

import { ICON, NAVBAR } from 'theme/config';

type IProps = LinkProps & ListItemButtonProps;

interface IListItemStyleProps extends IProps {
    component?: ElementType;
    to?: string;
    activeRoot?: boolean;
    activeSub?: boolean;
    subItem?: boolean;
    roles?: string[];
}

export const NavListItem = styled(ListItemButton, {
    shouldForwardProp: (prop) =>
        prop !== 'activeRoot' && prop !== 'activeSub' && prop !== 'subItem',
})<IListItemStyleProps>(({ activeRoot, activeSub, subItem, theme }) => ({
    ...theme.typography.body2,
    position: 'relative',
    height: NAVBAR.DASHBOARD_ITEM_ROOT_HEIGHT,
    textTransform: 'capitalize',
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(1.5),
    marginBottom: theme.spacing(0.5),
    color: theme.palette.text.secondary,
    borderRadius: theme.shape.borderRadius,
    // activeRoot
    ...(activeRoot && {
        ...theme.typography.subtitle2,
        color: theme.palette.primary.main,
        backgroundColor: alpha(theme.palette.primary.main, theme.palette.action.selectedOpacity),
    }),
    // activeSub
    ...(activeSub && {
        ...theme.typography.subtitle2,
        color: theme.palette.text.primary,
    }),
    // subItem
    ...(subItem && {
        height: NAVBAR.DASHBOARD_ITEM_SUB_HEIGHT,
    }),
}));

interface IListItemTextStyleProps extends ListItemButtonProps {
    isCollapse?: boolean;
}

export const ListItemTextStyle = styled(ListItemText, {
    shouldForwardProp: (prop) => prop !== 'isCollapse',
})<IListItemTextStyleProps>(({ isCollapse, theme }) => ({
    whiteSpace: 'nowrap',
    transition: theme.transitions.create(['width', 'opacity'], {
        duration: theme.transitions.duration.shorter,
    }),
    ...(isCollapse && {
        width: 0,
        opacity: 0,
    }),
}));

export const ListItemIconStyle = styled(ListItemIcon)({
    width: ICON.NAVBAR_ITEM,
    height: ICON.NAVBAR_ITEM,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    '& svg': { width: '100%', height: '100%' },
});
