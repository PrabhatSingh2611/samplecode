import React, { useState } from 'react';

import { Box } from '@mui/material';
import { styled } from '@mui/material/styles';

import useCollapseDrawer from 'hooks/useCollapseDrawer';
import useResponsive from 'hooks/useResponsive';
import useSettings from 'hooks/useSettings';
import DashboardHeader from 'layouts/dashboard/header';
import NavbarHorizontal from 'layouts/dashboard/navbar/NavbarHorizontal';
import NavbarVertical from 'layouts/dashboard/navbar/NavbarVertical';
import Routes from 'routes';
import { HEADER, NAVBAR } from 'theme/config';

type MainStyleProps = {
    collapseClick: boolean;
};

const MainStyle = styled('main', {
    shouldForwardProp: (prop) => prop !== 'collapseClick',
})<MainStyleProps>(({ collapseClick, theme }) => ({
    flexGrow: 1,
    paddingTop: HEADER.MOBILE_HEIGHT + 24,
    paddingBottom: HEADER.MOBILE_HEIGHT + 24,
    [theme.breakpoints.up('lg')]: {
        paddingLeft: 16,
        paddingRight: 16,
        paddingTop: HEADER.DASHBOARD_DESKTOP_HEIGHT + 24,
        paddingBottom: HEADER.DASHBOARD_DESKTOP_HEIGHT + 24,
        width: `calc(100% - ${NAVBAR.DASHBOARD_WIDTH}px)`,
        transition: theme.transitions.create('margin-left', {
            duration: theme.transitions.duration.shorter,
        }),
        ...(collapseClick && {
            marginLeft: NAVBAR.DASHBOARD_COLLAPSE_WIDTH,
        }),
    },
}));

export default function DashboardLayout(): JSX.Element {
    const { collapseClick, isCollapse } = useCollapseDrawer();

    const { themeLayout } = useSettings();

    const isDesktop = useResponsive('up', 'lg');

    const [open, setOpen] = useState(false);

    const verticalLayout = themeLayout === 'vertical';

    if (verticalLayout) {
        return (
            <>
                <DashboardHeader
                    onOpenSidebar={(): void => setOpen(true)}
                    verticalLayout={verticalLayout}
                />

                {isDesktop ? (
                    <NavbarHorizontal />
                ) : (
                    <NavbarVertical
                        isOpenSidebar={open}
                        onCloseSidebar={(): void => setOpen(false)}
                    />
                )}

                <Box
                    component="main"
                    sx={{
                        px: { lg: 2 },
                        pt: {
                            lg: `${HEADER.DASHBOARD_DESKTOP_HEIGHT + 80}px`,
                            xs: `${HEADER.MOBILE_HEIGHT + 24}px`,
                        },
                        pb: {
                            lg: `${HEADER.DASHBOARD_DESKTOP_HEIGHT + 24}px`,
                            xs: `${HEADER.MOBILE_HEIGHT + 24}px`,
                        },
                    }}
                >
                    <Routes />
                </Box>
            </>
        );
    }

    return (
        <Box
            sx={{
                display: { lg: 'flex' },
                minHeight: { lg: 1 },
            }}
        >
            <DashboardHeader isCollapse={isCollapse} onOpenSidebar={(): void => setOpen(true)} />

            <NavbarVertical isOpenSidebar={open} onCloseSidebar={(): void => setOpen(false)} />

            <MainStyle collapseClick={collapseClick}>{<Routes />}</MainStyle>
        </Box>
    );
}
