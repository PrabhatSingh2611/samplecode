import React from 'react';
import {
  FormControlLabel,
  FormControlLabelProps,
  Table,
  TableCell,
  TableCellProps,
  TableContainer,
  TableContainerProps,
  TableFooter,
  TableFooterProps,
  TableHead,
  TableHeadProps,
  TablePagination,
  TablePaginationProps,
  TableProps,
  TableRow,
  TableRowProps,
  TableSortLabel,
  TableSortLabelProps,
} from '@mui/material';
import TableBody from '@mui/material/TableBody';
import WStack from '../stack';
import WSkeleton from '../skeleton';
import WEmptyState from '../../widgets/empty-state';

export interface WTableProps extends TableProps {}
export interface WTableCellProps extends TableCellProps {}
export interface WTableContainerProps extends TableContainerProps {}
export interface WTableFooterProps extends TableFooterProps {}
export interface WTableHeadProps extends TableHeadProps {}
// TODO: Fix types (TH)
export type WTablePaginationProps = TablePaginationProps & {
  component: string;
};
export interface WTableRowProps extends TableRowProps {}
export interface WTableSortLabelProps extends TableSortLabelProps {}
export interface WFormControlTableLabelProps extends FormControlLabelProps {}
interface ITableEmptyRows {
  emptyRows: number;
  height?: number;
}

type TTableSkeleton = WTableRowProps & {
  rowHeight?: number;
  children?: JSX.Element;
};

export function WTable(props: WTableProps): JSX.Element {
  return <Table {...props} />;
}

// TODO: Fix types (VS)
WTable.Body = function (props: any): JSX.Element {
  return <TableBody {...props} />;
};
WTable.Cell = function (props: WTableCellProps): JSX.Element {
  return <TableCell {...props} />;
};
WTable.Container = function (props: WTableContainerProps): JSX.Element {
  return <TableContainer {...props} />;
};
WTable.Footer = function (props: WTableFooterProps): JSX.Element {
  return <TableFooter {...props} />;
};
WTable.Head = function (props: WTableHeadProps): JSX.Element {
  return <TableHead {...props} />;
};
WTable.Pagination = function (props: WTablePaginationProps): JSX.Element {
  return <TablePagination {...props} />;
};

WTable.Row = function (props: WTableRowProps): JSX.Element {
  return <TableRow {...props} />;
};
WTable.SortLabel = function (props: WTableSortLabelProps): JSX.Element {
  return <TableSortLabel {...props} />;
};
WTable.ControlLabel = function (
  props: WFormControlTableLabelProps
): JSX.Element {
  return <FormControlLabel {...props} />;
};

WTable.EmptyRows = ({ emptyRows, height }: ITableEmptyRows): JSX.Element => (
  <TableRow
    sx={{
      ...(height && {
        height: height * emptyRows,
      }),
    }}
  >
    <TableCell colSpan={9} />
  </TableRow>
);

WTable.Skeleton = (props: TTableSkeleton): JSX.Element => {
  const defaultRowHeight = 40;

  return (
    <WTable.Row {...props}>
      <WTable.Cell colSpan={12}>
        <WStack spacing={3} direction="row" alignItems="center">
          <WSkeleton
            variant="rectangular"
            width={65}
            height={65}
            sx={{ flexShrink: 0, borderRadius: 1 }}
          />
          <WSkeleton
            variant="text"
            width="100%"
            height={props.rowHeight || defaultRowHeight}
          />
          {props.children}
        </WStack>
      </WTable.Cell>
    </WTable.Row>
  );
};

WTable.NoData = (): JSX.Element => (
  <WTable.Row>
    <WTable.Cell colSpan={12}>
      <WEmptyState
        title="No matches found"
        subtitle="Change your search query or modify filters"
        sx={{
          '& span.MuiBox-root': { height: 160 },
        }}
      />
    </WTable.Cell>
  </WTable.Row>
);

export default WTable;
