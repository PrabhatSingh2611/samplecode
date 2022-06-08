import React, { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

import { styled, useTheme } from '@mui/material/styles';
import { WDrawer, WStack } from 'wdx';

import Logo from 'components/Logo';
import Scrollbar from 'components/Scrollbar';
import { NavSectionVertical } from 'components/nav-section';
import navConfig from 'features/navbar/components/NavConfig';
import useCollapseDrawer from 'hooks/useCollapseDrawer';
import useResponsive from 'hooks/useResponsive';
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

    const { isCollapse, collapseClick, collapseHover, onHoverEnter, onHoverLeave } =
        useCollapseDrawer();

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
            <WStack
                spacing={3}
                sx={{
                    flexShrink: 0,
                    py: 4,
                    px: 2.5,
                    ...(isCollapse && { alignItems: 'center' }),
                }}
            >
                <WStack
                    direction="row"
                    alignItems="center"
                    justifyContent="space-between"
                    sx={{ pl: 2 }}
                >
                    <Logo />

                    {/* TODO: Return if necessary and make a LOGO option for collapse (AU) */}
                    {/* {isDesktop && !isCollapse && (
                        <CollapseButton
                            onToggleCollapse={onToggleCollapse}
                            collapseClick={collapseClick}
                        />
                    )} */}
                </WStack>
            </WStack>

            <NavSectionVertical navConfig={navConfig} isCollapse={isCollapse} />
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
                <WDrawer
                    open={isOpenSidebar}
                    onClose={onCloseSidebar}
                    PaperProps={{ sx: { width: NAVBAR.DASHBOARD_WIDTH } }}
                >
                    {renderContent}
                </WDrawer>
            )}

            {isDesktop && (
                <WDrawer
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
                </WDrawer>
            )}
        </RootStyle>
    );
}
