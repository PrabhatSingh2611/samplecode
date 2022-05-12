import React, { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

import { Box, Stack, Drawer } from '@mui/material';
import { styled, useTheme } from '@mui/material/styles';

import Logo from 'components/Logo';
import Scrollbar from 'components/Scrollbar';
import { NavSectionVertical } from 'components/nav-section';
import useCollapseDrawer from 'hooks/useCollapseDrawer';
import useResponsive from 'hooks/useResponsive';
import CollapseButton from 'layouts/dashboard/navbar/CollapseButton';
import navConfig from 'layouts/dashboard/navbar/NavConfig';
import NavbarAccount from 'layouts/dashboard/navbar/NavbarAccount';
import NavbarDocs from 'layouts/dashboard/navbar/NavbarDocs';
import { NAVBAR } from 'theme/config';
import cssStyles from 'utils/cssStyles';

const RootStyle = styled('div')(({ theme }) => ({
    [theme.breakpoints.up('lg')]: {
        flexShrink: 0,
        transition: theme.transitions.create('width', {
            duration: theme.transitions.duration.shorter,
        }),
    },
}));

type IProps = {
    isOpenSidebar: boolean;
    onCloseSidebar: VoidFunction;
};

export default function NavbarVertical({ isOpenSidebar, onCloseSidebar }: IProps): JSX.Element {
    const theme = useTheme();

    const { pathname } = useLocation();

    const isDesktop = useResponsive('up', 'lg');

    const {
        isCollapse,
        collapseClick,
        collapseHover,
        onToggleCollapse,
        onHoverEnter,
        onHoverLeave,
    } = useCollapseDrawer();

    useEffect(() => {
        if (isOpenSidebar) {
            onCloseSidebar();
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [pathname]);

    const renderContent = (
        <Scrollbar
            sx={{
                height: 1,
                '& .simplebar-content': { display: 'flex', flexDirection: 'column', height: 1 },
            }}
        >
            <Stack
                spacing={3}
                sx={{
                    flexShrink: 0,
                    px: 2.5,
                    pt: 3,
                    pb: 2,
                    ...(isCollapse && { alignItems: 'center' }),
                }}
            >
                <Stack direction="row" alignItems="center" justifyContent="space-between">
                    <Logo />

                    {isDesktop && !isCollapse && (
                        <CollapseButton
                            onToggleCollapse={onToggleCollapse}
                            collapseClick={collapseClick}
                        />
                    )}
                </Stack>

                <NavbarAccount isCollapse={isCollapse} />
            </Stack>

            <NavSectionVertical navConfig={navConfig} isCollapse={isCollapse} />

            <Box sx={{ flexGrow: 1 }} />

            {!isCollapse && <NavbarDocs />}
        </Scrollbar>
    );

    return (
        <RootStyle
            sx={{
                width: {
                    lg: isCollapse ? NAVBAR.DASHBOARD_COLLAPSE_WIDTH : NAVBAR.DASHBOARD_WIDTH,
                },
                ...(collapseClick && {
                    position: 'absolute',
                }),
            }}
        >
            {!isDesktop && (
                <Drawer
                    open={isOpenSidebar}
                    onClose={onCloseSidebar}
                    PaperProps={{ sx: { width: NAVBAR.DASHBOARD_WIDTH } }}
                >
                    {renderContent}
                </Drawer>
            )}

            {isDesktop && (
                <Drawer
                    open
                    variant="persistent"
                    onMouseEnter={onHoverEnter}
                    onMouseLeave={onHoverLeave}
                    PaperProps={{
                        sx: {
                            width: NAVBAR.DASHBOARD_WIDTH,
                            borderRightStyle: 'dashed',
                            bgcolor: 'background.default',
                            transition: (theme) =>
                                theme.transitions.create('width', {
                                    duration: theme.transitions.duration.standard,
                                }),
                            ...(isCollapse && {
                                width: NAVBAR.DASHBOARD_COLLAPSE_WIDTH,
                            }),
                            ...(collapseHover && {
                                ...cssStyles(theme).bgBlur(),
                                boxShadow: (theme) => theme.customShadows.z24,
                            }),
                        },
                    }}
                >
                    {renderContent}
                </Drawer>
            )}
        </RootStyle>
    );
}
