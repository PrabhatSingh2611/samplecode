import React from 'react';

import { Icon, IconifyIcon } from '@iconify/react';
import { Box, BoxProps, SxProps } from '@mui/material';

interface IProps extends BoxProps {
    sx?: SxProps;
    icon: IconifyIcon | string;
}

export default function Iconify({ icon, sx, ...other }: IProps): JSX.Element {
    return <Box component={Icon} icon={icon} sx={{ ...sx }} {...other} />;
}
