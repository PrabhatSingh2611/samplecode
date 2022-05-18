import React from 'react';

import { IconButton } from '@mui/material';
import WMenuPopover from '../../../components/menu-popover';
import WIconify from '../../../components/iconify';
import { TMoreMenuActions } from '../base-table.widget';

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

      <WMenuPopover
        open={Boolean(open)}
        anchorEl={open}
        onClose={() => setOpen(null)}
        anchorOrigin={{ vertical: 'top', horizontal: 'left' }}
        transformOrigin={{ vertical: 'top', horizontal: 'right' }}
        arrow="right-top"
        sx={{
          mt: -1,
          width: 160,
          '& .MuiMenuItem-root': {
            px: 1,
            typography: 'body2',
            borderRadius: 0.75,
            '& svg': { mr: 2, width: 20, height: 20 },
          },
        }}
      >
        {actions({ onClose: () => setOpen(null) })}
      </WMenuPopover>
    </>
  );
}
