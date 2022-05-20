import React, { ReactNode } from 'react';

import { WIconButton } from '../button';
import WDrawer, { WDrawerProps } from '../drawer';
import WBox from '../box';
import WTypography from '../typography';
import WStack from '../stack';
import { SxProps, Theme } from '@mui/material';
import WIcon from '../icon';

export interface WActionsDrawerProps extends WDrawerProps {
  title?: string;
  actions?: JSX.Element
  children: ReactNode;
  isOpened: boolean;
  childSx?: SxProps<Theme>,
  onClose: () => void;
}


function WActionsDrawer({
  title,
  children,
  isOpened,
  onClose,
  childSx,
  ...props
}: WActionsDrawerProps): JSX.Element {
  return (
    <WDrawer {...props} open={isOpened} onClose={onClose} anchor="right">
      <WBox sx={{display: 'grid', gridTemplateRows: 'max-content 1fr max-content', height: 1, width: 660, ...childSx}}>
        <WStack
          direction="row"
          alignItems="center"
          justifyContent="space-between"
          sx={(theme: Theme)=>({px: 4, py: 1.5, minHeight: '70px', borderBottom: `1px solid ${theme.palette.divider}`})}
        >
          <WTypography component='div' variant="h6" sx={{ flexGrow: 1 }}>
            {title}
          </WTypography>

          <WIconButton onClick={onClose}>
            <WIcon name="close" fontSize='small' />
          </WIconButton>
        </WStack>

        {children}
      </WBox>
    </WDrawer>
  );
}

WActionsDrawer.Content = function WActionsDrawerContent({children}:{children: ReactNode}):JSX.Element {
    return (
        <WBox sx={{py: 3, px: 4, overflow: 'auto'}}>
            {children}
        </WBox>
    )
}

WActionsDrawer.Footer = function WActionsDrawerContent({children}:{children: ReactNode}):JSX.Element {
    return (
        <WBox sx={{p: 3, display: 'flex', justifyContent: 'flex-end', alignItems: 'center', gap: 1.5}}>
            {children}
        </WBox>
    )
}


export default WActionsDrawer;
