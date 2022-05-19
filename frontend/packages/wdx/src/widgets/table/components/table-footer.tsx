import React, { ChangeEvent } from 'react';
import WBox from '../../../components/box';
import WTable from '../../../components/table';
import { Switch, SxProps } from '@mui/material';
import { onChangeRowsPerPage as handleChangeRowsPerPage } from '../utils';
import { Theme } from '@mui/material/styles';

interface IWTableFooter {
  paginationOptions?: number[];
  rowsPerPage: number;
  currentPage: number;
  onChangePage: (page: number) => void;
  onChangeRowsPerPage: (rowsPerPage: number) => void;
  dense: boolean;
  onChangeDense: (event: ChangeEvent<HTMLInputElement>, value: boolean) => void;
  wrapperSx?: SxProps<Theme>;
  totalPageCount: number;
}

export default function WTableFooter({
  paginationOptions,
  rowsPerPage,
  currentPage,
  onChangePage,
  onChangeRowsPerPage,
  dense,
  onChangeDense,
  wrapperSx,
  totalPageCount,
}: IWTableFooter): JSX.Element {
  // TODO Fix types (VS)
  const onChangeRows = (event: any) =>
    handleChangeRowsPerPage(event, onChangeRowsPerPage, onChangePage);
  return (
    <WBox sx={{ position: 'relative', ...wrapperSx }}>
      <WTable.Pagination
        rowsPerPageOptions={paginationOptions || [5, 10, 25]}
        component={'div'}
        count={totalPageCount}
        rowsPerPage={rowsPerPage}
        page={currentPage}
        onPageChange={(_: any, newPage: number) => onChangePage(newPage)}
        onRowsPerPageChange={onChangeRows}
      />
      <WTable.ControlLabel
        control={<Switch checked={dense} onChange={onChangeDense} />}
        label="Dense"
        sx={{ px: 3, py: 1.5, top: 0, position: { md: 'absolute' } }}
      />
    </WBox>
  );
}
