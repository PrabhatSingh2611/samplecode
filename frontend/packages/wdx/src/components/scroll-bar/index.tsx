import React from 'react';

import { Box, SxProps } from '@mui/material';
import { alpha } from '@mui/material/styles';
import SimpleBarReact, { Props as ScrollbarProps } from 'simplebar-react';
import styled from '@emotion/styled';

const RootStyle = styled('div')(() => ({
  flexGrow: 1,
  height: '100%',
  overflow: 'hidden',
}));

const SimpleBarStyle = styled(SimpleBarReact)(() => ({
  maxHeight: '100%',
  '& .simplebar-scrollbar': {
    '&:before': {
      backgroundColor: alpha('#637381', 0.48),
    },
    '&.simplebar-visible:before': {
      opacity: 1,
    },
  },
  '& .simplebar-placeholder': {
    position: 'absolute',
  },
  '& .simplebar-track.simplebar-vertical': {
    width: 10,
  },
  '& .simplebar-track.simplebar-horizontal .simplebar-scrollbar': {
    height: 6,
  },
  '& .simplebar-mask': {
    zIndex: 'inherit',
  },
}));

export interface IWScrollbarContainerProps extends ScrollbarProps {
  children: React.ReactNode;
  sx?: SxProps;
}

export default function WScrollbarContainer({
  children,
  sx,
  ...other
}: IWScrollbarContainerProps): JSX.Element {
  const userAgent =
    typeof navigator === 'undefined' ? 'SSR' : navigator.userAgent;

  const isMobile =
    /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(
      userAgent
    );

  if (isMobile) {
    return (
      <Box sx={{ overflowX: 'auto', ...sx }} {...other}>
        {children}
      </Box>
    );
  }

  return (
    <RootStyle>
      <SimpleBarStyle timeout={500} clickOnTrack={false} {...other}>
        {children}
      </SimpleBarStyle>
    </RootStyle>
  );
}
