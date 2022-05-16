import React, { memo } from 'react';

import { Container, AppBar } from '@mui/material';
import { styled } from '@mui/material/styles';

import { NavSectionHorizontal } from 'components/nav-section';
import navConfig from 'layouts/app/navbar/NavConfig';
import { HEADER } from 'theme/config';

const RootStyle = styled(AppBar)(({ theme }) => ({
    transition: theme.transitions.create('top', {
        easing: theme.transitions.easing.easeInOut,
        duration: theme.transitions.duration.shorter,
    }),
    width: '100%',
    position: 'fixed',
    zIndex: theme.zIndex.appBar,
    padding: theme.spacing(1, 0),
    boxShadow: theme.customShadows.z8,
    top: HEADER.DASHBOARD_DESKTOP_OFFSET_HEIGHT,
    backgroundColor: theme.palette.background.default,
}));

function NavbarHorizontal(): JSX.Element {
    return (
        <RootStyle>
            <Container maxWidth={false}>
                <NavSectionHorizontal navConfig={navConfig} />
            </Container>
        </RootStyle>
    );
}

export default memo(NavbarHorizontal);
