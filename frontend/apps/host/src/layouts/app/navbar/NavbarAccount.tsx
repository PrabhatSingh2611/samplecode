import React from 'react';

import { Box, Link, Typography, Avatar } from '@mui/material';
import { styled } from '@mui/material/styles';

const RootStyle = styled('div')(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(2, 2.5),
    borderRadius: Number(theme.shape.borderRadius) * 1.5,
    backgroundColor: theme.palette.grey[500_12],
    transition: theme.transitions.create('opacity', {
        duration: theme.transitions.duration.shorter,
    }),
}));

interface IProps {
    isCollapse: boolean | undefined;
}

export default function NavbarAccount({ isCollapse }: IProps): JSX.Element {
    return (
        <Link underline="none" color="inherit">
            <RootStyle
                sx={{
                    ...(isCollapse && {
                        bgcolor: 'transparent',
                    }),
                }}
            >
                <Avatar
                    src="https://minimal-assets-api-dev.vercel.app/assets/images/avatars/avatar_5.jpg"
                    alt="Rayan Moran"
                />

                <Box
                    sx={{
                        ml: 2,
                        transition: (theme) =>
                            theme.transitions.create('width', {
                                duration: theme.transitions.duration.shorter,
                            }),
                        ...(isCollapse && {
                            width: 0,
                            ml: 0,
                        }),
                    }}
                >
                    <Typography variant="subtitle2" noWrap>
                        Rayan Moran
                    </Typography>
                    <Typography variant="body2" noWrap sx={{ color: 'text.secondary' }}>
                        user
                    </Typography>
                </Box>
            </RootStyle>
        </Link>
    );
}
