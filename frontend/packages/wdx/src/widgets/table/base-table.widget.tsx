import React, { ChangeEvent, useState } from 'react';
import WTable from '../../components/table';
import { WTableHead } from './components/table-head';
import { emptyRows, onSelectRow, onSort } from './utils';
import WTableRow from './components/table-row';
import WTableFooter from './components/table-footer';
import WTableToolBar from './components/table-toolbar';
import WTableSelectedActions from './components/table-selected-actions';
import WCard from '../../components/card';

export enum Order {
  ASC = 'asc',
  DESC = 'desc',
}

export enum AlignTableCell {
  LEFT = 'left',
  CENTER = 'center',
  RIGHT = 'right',
}

export interface IHeaderCellData {
  id: string;
  align?: AlignTableCell;
  width?: string;
  minWidth?: string;
  label: string;
}

export type TMoreMenuActions = ({
  onClose,
}: {
  onClose: () => void;
}) => React.ReactNode;

export interface IWBaseTableProps<T extends { id: string }> {
  currentPage: number;
  rowsPerPage: number;
  headerData: IHeaderCellData[];
  bodyData: T[];
  totalPageCount: number;
  setCurrentPage: (page: number) => void;
  setRowsPerPage: (rowsPerPage: number) => void;
  order?: Order;
  orderBy?: string;
  selected?: string[];
  isCheckable?: boolean;
  renderBodyRow?: (rowData: T) => React.ReactNode;
  renderFooter?: React.ReactNode;
  withFooter?: boolean;
  toolbarElements?: React.ReactNode;
  setSelected?: (selected: string[]) => void;
  setOrderBy?: (orderBy: string) => void;
  setOrder?: (order: Order) => void;
  selectActions?: React.ReactNode;
  moreMenuActions?: TMoreMenuActions;
}

export default function WBaseTable<T extends { id: string }>({
  currentPage = 0,
  order,
  orderBy,
  rowsPerPage,
  selected,
  headerData,
  bodyData,
  isCheckable = true,
  renderFooter,
  withFooter = true,
  totalPageCount,
  toolbarElements,
  setCurrentPage,
  setRowsPerPage,
  setSelected,
  setOrderBy,
  setOrder,
  renderBodyRow,
  selectActions,
  moreMenuActions,
}: IWBaseTableProps<T>): JSX.Element {
  const [dense, setDense] = useState(false);
  const onChangeDense = (_: ChangeEvent<HTMLInputElement>, value: boolean) =>
    setDense(value);

  const onSelectAllRows = ({
    isChecked,
    bodyData,
  }: {
    isChecked: boolean;
    bodyData: T[];
  }) => {
    if (!setSelected) {
      return;
    }

    if (isChecked) {
      const newChecked = bodyData.map((tableRow: T) => tableRow.id);
      return setSelected(newChecked);
    }
    return setSelected([]);
  };

  const denseHeight = dense ? 34 : 54;
  const tableSize = dense ? 'small' : 'medium';
  const onRowSelect =
    selected !== undefined && setSelected
      ? (id: string) => onSelectRow({ id, selected, setSelected })
      : undefined;
  const footerProps = {
    totalPageCount,
    rowsPerPage,
    currentPage,
    dense,
    onChangePage: setCurrentPage,
    onChangeRowsPerPage: setRowsPerPage,
    onChangeDense: onChangeDense,
  };

  const footer = renderFooter ? (
    renderFooter
  ) : (
    <WTableFooter {...footerProps} />
  );

  const emptyRowsCount = emptyRows(rowsPerPage, bodyData.length);
  const onSortHandler =
    orderBy && setOrderBy && setOrder && order
      ? (id: string): void => {
          onSort({ id, orderBy, setOrderBy, setOrder, order });
        }
      : undefined;
  return (
    <WCard>
      {!!toolbarElements && <WTableToolBar toolbarElements={toolbarElements} />}
      <WTable.Container sx={{ minWidth: 800, position: 'relative' }}>
        {!!selectActions && !!selected?.length && (
          <WTableSelectedActions
            dense={dense}
            numSelected={selected.length}
            rowCount={bodyData.length}
            onSelectAllRows={(isChecked: boolean) =>
              onSelectAllRows({
                isChecked,
                bodyData,
              })
            }
            actions={selectActions}
          />
        )}
        <WTable size={tableSize}>
          <WTableHead
            headerData={headerData}
            rowCount={headerData.length}
            isCheckable={isCheckable}
            onSortHandler={(value: IHeaderCellData) => {
              onSortHandler?.(value.id);
            }}
            onSelectAllRows={({ isChecked }: any): void =>
              onSelectAllRows({
                isChecked,
                bodyData,
              })
            }
            orderBy={orderBy}
            selectedCount={selected?.length}
            order={order}
            addMoreActionsCell={!!moreMenuActions}
          />
          <WTable.Body>
            {bodyData.map((rowData: T) =>
              !!renderBodyRow ? (
                <TableRowComponent
                  rowData={rowData}
                  renderBodyRow={renderBodyRow}
                  key={rowData.id}
                />
              ) : (
                <WTableRow
                  hover={isCheckable}
                  onSelectRow={onRowSelect}
                  selected={
                    !!selected &&
                    !!selected?.length &&
                    selected.includes(rowData.id)
                  }
                  rowData={rowData}
                  isCheckable={isCheckable}
                  moreMenuActions={moreMenuActions}
                  key={rowData.id}
                  headerData={headerData}
                />
              )
            )}

            {!!emptyRowsCount && (
              <WTable.EmptyRows
                emptyRows={emptyRowsCount}
                height={denseHeight}
              />
            )}
          </WTable.Body>
        </WTable>
      </WTable.Container>
      {withFooter && footer}
    </WCard>
  );
}

//TODO: Find another solution to avoid extrawrap for passing key (VS)
function TableRowComponent({ renderBodyRow, rowData }: any): JSX.Element {
  return renderBodyRow(rowData);
}
