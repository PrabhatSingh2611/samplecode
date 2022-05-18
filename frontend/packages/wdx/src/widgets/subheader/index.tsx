import React from 'react';
import WStack from '../../components/stack';
import WTypography from '../../components/typography';
import { SxProps, Theme } from '@mui/material';

export interface WSubHeaderProps {
  title?: string;
  breadcrumbs?: JSX.Element;
  actions?: JSX.Element;
  sx?: SxProps<Theme>,
}

function WSubHeader({
  title,
  breadcrumbs,
  actions,
  sx
}: WSubHeaderProps): JSX.Element {

  return (
    <WStack
      direction="row"
      justifyContent="space-between"
      alignItems="center"
      spacing={2}
      sx={{pb: 3, ...sx}}
    >
      <WStack gap={1}>
        {title && <WTypography variant='h4' component="h1">{title}</WTypography>}
        {breadcrumbs}
      </WStack>

      {actions && <WStack direction="row" alignItems="center" spacing={2}>{actions}</WStack>}
    </WStack>
  );
}

export default WSubHeader;
