import React from 'react';
import { NavLink as RouterLink } from 'react-router-dom';

import { Box, Link, ListItemText, Typography, Tooltip } from '@mui/material';

import Iconify from 'components/Iconify';
import { NavItemProps } from 'components/nav-section/type';
import { isExternalLink } from 'components/nav-section/utils/nav-section.utils';
import {
    NavListItem,
    ListItemTextStyle,
    ListItemIconStyle,
} from 'components/nav-section/vertical/NavListItem';

export function NavItemRoot({
    item,
    isCollapse,
    open = false,
    active,
    onOpen,
}: NavItemProps): JSX.Element {
    const { title, path, icon, info, children, disabled, caption, roles } = item;

    const renderContent = (
        <>
            {icon && <ListItemIconStyle>{icon}</ListItemIconStyle>}
            <ListItemTextStyle
                disableTypography
                primary={title}
                secondary={
                    <Tooltip title={caption || ''} arrow>
                        <Typography
                            noWrap
                            variant="caption"
                            component="div"
                            sx={{ textTransform: 'initial', color: 'text.secondary' }}
                        >
                            {caption}
                        </Typography>
                    </Tooltip>
                }
                isCollapse={isCollapse}
            />
            {!isCollapse && (
                <>
                    {info ?? null}
                    {children && <ArrowIcon open={open} />}
                </>
            )}
        </>
    );

    if (children) {
        return (
            <NavListItem onClick={onOpen} activeRoot={active} disabled={disabled} roles={roles}>
                {renderContent}
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
            {renderContent}
        </NavListItem>
    ) : (
        <NavListItem
            component={RouterLink}
            to={path}
            activeRoot={active}
            disabled={disabled}
            roles={roles}
        >
            {renderContent}
        </NavListItem>
    );
}

type NavItemSubProps = Omit<NavItemProps, 'isCollapse'>;

export function NavItemSub({
    item,
    open = false,
    active = false,
    onOpen,
}: NavItemSubProps): JSX.Element {
    const { title, path, info, children, disabled, caption, roles } = item;

    const renderContent = (
        <>
            <DotIcon active={active} />
            <ListItemText
                disableTypography
                primary={title}
                secondary={
                    <Tooltip title={caption || ''} arrow>
                        <Typography
                            noWrap
                            variant="caption"
                            component="div"
                            sx={{ textTransform: 'initial', color: 'text.secondary' }}
                        >
                            {caption}
                        </Typography>
                    </Tooltip>
                }
            />
            {info ?? null}
            {children && <ArrowIcon open={open} />}
        </>
    );

    if (children) {
        return (
            <NavListItem
                onClick={onOpen}
                activeSub={active}
                subItem
                disabled={disabled}
                roles={roles}
            >
                {renderContent}
            </NavListItem>
        );
    }

    return isExternalLink(path) ? (
        <NavListItem
            component={Link}
            href={path}
            target="_blank"
            rel="noopener"
            subItem
            disabled={disabled}
            roles={roles}
        >
            {renderContent}
        </NavListItem>
    ) : (
        <NavListItem
            component={RouterLink}
            to={path}
            activeSub={active}
            subItem
            disabled={disabled}
            roles={roles}
        >
            {renderContent}
        </NavListItem>
    );
}

type DotIconProps = {
    active: boolean;
};

function DotIcon({ active }: DotIconProps): JSX.Element {
    return (
        <ListItemIconStyle>
            <Box
                component="span"
                sx={{
                    width: 4,
                    height: 4,
                    borderRadius: '50%',
                    transition: (theme) =>
                        theme.transitions.create('transform', {
                            duration: theme.transitions.duration.shorter,
                        }),
                    bgcolor: 'text.disabled',
                    ...(active && {
                        transform: 'scale(2)',
                        bgcolor: 'primary.main',
                    }),
                }}
            />
        </ListItemIconStyle>
    );
}

interface IArrowIconProps {
    open: boolean;
}

function ArrowIcon({ open }: IArrowIconProps): JSX.Element {
    return (
        <Iconify
            icon={open ? 'eva:arrow-ios-downward-fill' : 'eva:arrow-ios-forward-fill'}
            sx={{ width: 16, height: 16, ml: 1 }}
        />
    );
}
