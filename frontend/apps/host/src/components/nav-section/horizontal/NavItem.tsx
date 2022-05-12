import React, { ReactElement, forwardRef } from 'react';
import { NavLink as RouterLink } from 'react-router-dom';

import { Box, Link } from '@mui/material';

import Iconify from 'components/Iconify';
import { NavListItem } from 'components/nav-section/horizontal/NavListItem';
import { NavItemProps } from 'components/nav-section/type';
import { isExternalLink } from 'components/nav-section/utils/nav-section.utils';
import { ICON } from 'theme/config';

const NavItemRoot = forwardRef<HTMLButtonElement & HTMLAnchorElement, NavItemProps>(
    ({ item, active, open, onMouseEnter, onMouseLeave }, ref) => {
        const { title, path, icon, children, disabled, roles } = item;

        if (children) {
            return (
                <NavListItem
                    ref={ref}
                    open={open}
                    activeRoot={active}
                    onMouseEnter={onMouseEnter}
                    onMouseLeave={onMouseLeave}
                    disabled={disabled}
                    roles={roles}
                >
                    <NavItemContent icon={icon} title={title}>
                        {children}
                    </NavItemContent>
                </NavListItem>
            );
        }

        return isExternalLink(path) ? (
            <NavListItem
                component={Link}
                href={path}
                target="_blank"
                rel="noopener"
                disabled={disabled}
                roles={roles}
            >
                <NavItemContent icon={icon} title={title}>
                    {children}
                </NavItemContent>
            </NavListItem>
        ) : (
            <NavListItem
                component={RouterLink}
                to={path}
                activeRoot={active}
                disabled={disabled}
                roles={roles}
            >
                <NavItemContent icon={icon} title={title}>
                    {children}
                </NavItemContent>
            </NavListItem>
        );
    },
);
NavItemRoot.displayName = 'NavItemRoot';
export { NavItemRoot };

const NavItemSub = forwardRef<HTMLButtonElement & HTMLAnchorElement, NavItemProps>(
    ({ item, active, open, onMouseEnter, onMouseLeave }, ref): JSX.Element => {
        const { title, path, icon, children, disabled, roles } = item;

        if (children) {
            return (
                <NavListItem
                    ref={ref}
                    subItem
                    disableRipple
                    open={open}
                    activeSub={active}
                    onMouseEnter={onMouseEnter}
                    onMouseLeave={onMouseLeave}
                    disabled={disabled}
                    roles={roles}
                >
                    <NavItemContent icon={icon} title={title} subItem>
                        {children}
                    </NavItemContent>
                </NavListItem>
            );
        }

        return isExternalLink(path) ? (
            <NavListItem
                subItem
                href={path}
                disableRipple
                rel="noopener"
                target="_blank"
                component={Link}
                disabled={disabled}
                roles={roles}
            >
                <NavItemContent icon={icon} title={title} subItem>
                    {children}
                </NavItemContent>
            </NavListItem>
        ) : (
            <NavListItem
                disableRipple
                component={RouterLink}
                to={path}
                activeSub={active}
                subItem
                disabled={disabled}
                roles={roles}
            >
                <NavItemContent icon={icon} title={title} subItem>
                    {children}
                </NavItemContent>
            </NavListItem>
        );
    },
);

NavItemSub.displayName = 'NavItemSub';
export { NavItemSub };

interface INavItemContentProps {
    title: string;
    icon?: ReactElement;
    children?: { title: string; path: string }[];
    subItem?: boolean;
}

function NavItemContent({ icon, title, children, subItem }: INavItemContentProps): JSX.Element {
    return (
        <>
            {icon && (
                <Box
                    component="span"
                    sx={{
                        width: ICON.NAVBAR_ITEM_HORIZONTAL,
                        height: ICON.NAVBAR_ITEM_HORIZONTAL,
                        mr: 1,
                        '& svg': { width: '100%', height: '100%' },
                    }}
                >
                    {icon}
                </Box>
            )}

            {title}

            {children && (
                <Iconify
                    icon={subItem ? 'eva:chevron-right-fill' : 'eva:chevron-down-fill'}
                    sx={{
                        width: ICON.NAVBAR_ITEM_HORIZONTAL,
                        height: ICON.NAVBAR_ITEM_HORIZONTAL,
                        ml: 0.5,
                    }}
                />
            )}
        </>
    );
}
