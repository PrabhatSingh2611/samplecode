import React from 'react'
import { Icon, IconifyIcon } from '@iconify/react';
import { Box, BoxProps, SxProps } from '@mui/material';

export interface WIconifyProps extends BoxProps {
  sx?: SxProps;
  icon: IconifyIcon | string;
}

export default function WIconify({ icon, sx, ...other }: WIconifyProps) {
  return <Box component={Icon} icon={icon} sx={{ ...sx }} {...other} />;
}
