import React from 'react';
import WTable from '../../../components/table';
import { SxProps, TableSortLabel } from '@mui/material';
import WBox from '../../../components/box';
import { Align, IHeaderCellData, Order } from '../base-table.widget';
import { Theme } from '@mui/material/styles';
import WCheckbox from '../../../components/checkbox';

interface IWTableHead {
  headerData: IHeaderCellData[];
  isCheckable: boolean;
  onSortHandler: (value: IHeaderCellData) => void;
  onSelectAllRows: (value: {
    element: EventTarget & HTMLInputElement;
    isChecked: boolean;
  }) => void;
  rowCount: number;
  selectedCount?: number;
  orderBy?: string;
  order?: Order;
  sx?: SxProps<Theme>;
}

const visuallyHiddenSortingStyles = {
  border: 0,
  margin: -1,
  padding: 0,
  width: '1px',
  height: '1px',
  overflow: 'hidden',
  position: 'absolute',
  whiteSpace: 'nowrap',
  clip: 'rect(0 0 0 0)',
} as const;

export function WTableHead({
  headerData,
  isCheckable,
  onSortHandler,
  onSelectAllRows,
  rowCount,
  selectedCount,
  orderBy,
  order,
  sx,
}: IWTableHead): JSX.Element {
  const showCheckbox =
    !!onSelectAllRows && isCheckable && selectedCount !== undefined;
  return (
    <WTable.Head sx={sx}>
      <WTable.Row selected={true}>
        {showCheckbox && (
          <WTable.Cell padding="checkbox">
            <WCheckbox
              indeterminate={
                !!selectedCount && selectedCount > 0 && selectedCount < rowCount
              }
              checked={rowCount > 0 && selectedCount === rowCount}
              onChange={(
                event: React.ChangeEvent<HTMLInputElement>,
                isChecked
              ) => onSelectAllRows({ element: event.target, isChecked })}
            />
          </WTable.Cell>
        )}

        {headerData.map((headCellData: any) => (
          <WTable.Cell
            key={headCellData.id}
            align={headCellData.align || Align.LEFT}
            sortDirection={orderBy === headCellData.id ? order : false}
            sx={{ width: headCellData.width, minWidth: headCellData.minWidth }}
          >
            {onSortHandler ? (
              <WTableHead.TableSort
                cellData={headCellData}
                order={order}
                onSortHandler={onSortHandler}
                orderBy={orderBy}
              />
            ) : (
              headCellData.label
            )}
          </WTable.Cell>
        ))}
      </WTable.Row>
    </WTable.Head>
  );
}

WTableHead.TableSort = ({ cellData, orderBy, order, onSortHandler }: any) => {
  const sortOrder = order === 'desc' ? 'sorted descending' : 'sorted ascending';

  return (
    <TableSortLabel
      hideSortIcon
      active={orderBy === cellData?.id}
      direction={orderBy === cellData?.id ? order : 'asc'}
      onClick={() => onSortHandler(cellData)}
      sx={{ textTransform: 'capitalize' }}
    >
      {cellData?.label}
      {orderBy === cellData?.id ? (
        <WBox sx={{ ...visuallyHiddenSortingStyles }}>{sortOrder}</WBox>
      ) : null}
    </TableSortLabel>
  );
};
