import React from 'react';
import WStack from '../../components/stack';
import WTypography from '../../components/typography';
import WEmptyStateIllustration from '../../assets/illustration_empty_state';
import { SxProps, Theme } from '@mui/material';

export interface WEmptyStateProps {
  title?: string;
  subtitle?: string;
  actions?: JSX.Element;
  shouldShowImage?: boolean;
  sx?: SxProps<Theme>,
}

function WEmptyState({
  title,
  subtitle,
  actions,
  shouldShowImage = true,
  sx
}: WEmptyStateProps): JSX.Element {

  return (
    <WStack
      alignItems="center"
      justifyContent="center"
      sx={{p: 3, m: 'auto', maxWidth: '800px', ...sx}}
    >
      {shouldShowImage && <WEmptyStateIllustration sx={{mb: 3}}/>}
      {title && <WTypography variant='h3' component="div" align="center">{title}</WTypography>}
      {subtitle && <WTypography variant='h5' component="div" align="center" color={(theme:Theme) => theme.palette.grey[500]}>{subtitle}</WTypography>}
      {actions && <WStack direction="row" alignItems="center" spacing={2} mt={3}>{actions}</WStack>}
    </WStack>
  );
}

export default WEmptyState;
