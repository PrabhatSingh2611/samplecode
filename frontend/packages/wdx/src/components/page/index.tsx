import React, { forwardRef, ReactNode } from 'react';
import { Helmet } from 'react-helmet-async';
import { Box, BoxProps } from '@mui/material';

export interface WPageProps extends BoxProps {
  children: ReactNode;
  meta?: ReactNode;
  title: string;
}

export default forwardRef<HTMLDivElement, WPageProps>(({ children, title = '', meta, ...other }, ref) :JSX.Element => (
  <>
    <Helmet>
      <title>{title}</title>
      {meta}
    </Helmet>

    <Box ref={ref} {...other}>
      {children}
    </Box>
  </>
));

