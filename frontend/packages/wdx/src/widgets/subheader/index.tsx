import React from 'react';
import WStack from '../../components/stack';
import WTypography from '../../components/typography';
import { SxProps, Theme } from '@mui/material';

export interface WSubHeaderProps {
  title?: string;
  subtitle?: string;
  breadcrumbs?: JSX.Element;
  actions?: JSX.Element;
  sx?: SxProps<Theme>,
}

function WSubHeader({
  title,
  breadcrumbs,
  subtitle,
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
      <WStack gap={0.5}>
        {title && <WTypography variant='h4' component="h1">{title}</WTypography>}
        {subtitle && <WTypography
                        component='div'
                        variant="body2"
                        sx={(theme:Theme)=> ({color: theme.palette.grey[600]})}
                    >{subtitle}</WTypography>}
        {breadcrumbs}
      </WStack>

      {actions && <WStack direction="row" alignItems="center" mt={0.5} spacing={2}>{actions}</WStack>}
    </WStack>
  );
}

export default WSubHeader;
