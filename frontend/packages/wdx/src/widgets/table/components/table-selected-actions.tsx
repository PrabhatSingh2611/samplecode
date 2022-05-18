import React from 'react';
import WStack from '../../../components/stack';
import WTypography from '../../../components/typography';
import Checkbox from '@mui/material/Checkbox';
import { SxProps } from '@mui/material';
import { Theme } from '@mui/material/styles';

interface IWTableSelectedActions {
  sx?: SxProps<Theme>;
  dense: boolean;
  numSelected: number;
  rowCount: number;
  onSelectAllRows: (value: boolean) => void;
  actions: React.ReactNode;
}

export default function WTableSelectedActions({
  sx,
  dense,
  numSelected,
  rowCount,
  onSelectAllRows,
  actions,
  ...other
}: IWTableSelectedActions): JSX.Element {
  return (
    <WStack
      direction="row"
      alignItems="center"
      sx={{
        px: 2,
        top: 0,
        left: 8,
        right: 8,
        zIndex: 9,
        height: 58,
        borderRadius: 1,
        position: 'absolute',
        bgcolor: 'primary.lighter',
        ...(dense && {
          pl: 1,
          height: 38,
        }),
        ...sx,
      }}
      {...other}
    >
      <Checkbox
        indeterminate={numSelected > 0 && numSelected < rowCount}
        checked={rowCount > 0 && numSelected === rowCount}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) =>
          onSelectAllRows(event.target.checked)
        }
      />
      <WTypography
        variant="subtitle1"
        sx={{
          ml: 2,
          flexGrow: 1,
          color: 'primary.main',
          ...(dense && {
            ml: 3,
          }),
        }}
      >
        {numSelected} selected
      </WTypography>
      {actions}
    </WStack>
  );
}
