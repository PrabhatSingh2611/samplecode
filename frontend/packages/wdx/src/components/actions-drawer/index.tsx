import React, { ReactNode } from 'react';

import WIconButton from '../icon-button';
import WDrawer from '../drawer';
import WBox from '../box';
import WTypography from '../typography';
import WStack from '../stack';
import WDivider from '../divider';
import WIconify from '../iconify';

export interface WActionsDrawerProps {
  title?: string;
  children: ReactNode;
  isOpened: boolean;
  onClose: () => void;
}

const contentSX = { py: 2, pr: 1, pl: 2.5 };

function WActionsDrawer({
  title,
  children,
  isOpened,
  onClose,
}: WActionsDrawerProps): JSX.Element {
  return (
    <WDrawer open={isOpened} onClose={onClose} anchor="right">
      <WBox sx={{ width: 660 }}>
        <WStack
          direction="row"
          alignItems="center"
          justifyContent="space-between"
          sx={contentSX}
        >
          <WTypography variant="subtitle1" sx={{ flexGrow: 1 }}>
            {title}
          </WTypography>

          <WIconButton onClick={onClose}>
            <WIconify icon={'eva:close-fill'} width={20} height={20} />
          </WIconButton>
        </WStack>

        <WDivider />

        <WBox sx={contentSX}>{children}</WBox>
      </WBox>
    </WDrawer>
  );
}

export default WActionsDrawer;
