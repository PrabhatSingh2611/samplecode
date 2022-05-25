import React from 'react';

import { IconButton } from '@mui/material';
import WIconify from '../../../components/iconify';
import { TMoreMenuActions } from '../base-table.widget';
import WPopover from '../../../components/popover';

interface IWTableMoreMenu {
  actions: TMoreMenuActions;
  open?: HTMLElement | null;
  setOpen: (open: HTMLElement | null) => void;
}

export default function WTableMoreMenu({
  actions,
  open,
  setOpen,
}: IWTableMoreMenu) {
  return (
    <>
      <IconButton
        onClick={(e: React.MouseEvent<HTMLButtonElement>) =>
          setOpen(e.currentTarget)
        }
      >
        <WIconify icon={'eva:more-vertical-fill'} width={20} height={20} />
      </IconButton>
      <WPopover
        open={Boolean(open)}
        anchorEl={open}
        onClose={() => setOpen(null)}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
        transformOrigin={{ vertical: 'top', horizontal: 'right' }}
        arrow="top-right"
        sx={{
          ml: 1,
          '& .MuiMenuItem-root': {
            px: 1,
            typography: 'body2',
            borderRadius: 0.75,
            '& svg': { mr: 2, width: 20, height: 20 },
          },
        }}
      >
        {actions({ onClose: () => setOpen(null) })}
      </WPopover>
    </>
  );
}
