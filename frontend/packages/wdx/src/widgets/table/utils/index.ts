import React from 'react';
import { Order } from '../base-table.widget';

export interface IOnSelectRow {
  id: string;
  selected: string[];
  setSelected: (selected: string[]) => void;
}

export const onSelectRow = ({
  id,
  selected,
  setSelected,
}: IOnSelectRow): void => {
  const selectedIndex = selected.indexOf(id);

  let newSelected: string[] = [];

  if (selectedIndex === -1) {
    newSelected = newSelected.concat(selected, id);
  } else if (selectedIndex === 0) {
    newSelected = newSelected.concat(selected.slice(1));
  } else if (selectedIndex === selected.length - 1) {
    newSelected = newSelected.concat(selected.slice(0, -1));
  } else if (selectedIndex > 0) {
    newSelected = newSelected.concat(
      selected.slice(0, selectedIndex),
      selected.slice(selectedIndex + 1)
    );
  }
  setSelected(newSelected);
};

export interface IOnSort {
  id: string;
  orderBy: string;
  order: Order;
  setOrder: (order: Order) => void;
  setOrderBy?: (orderBy: string) => void;
}

export const onSort = ({
  id,
  orderBy,
  order,
  setOrder,
  setOrderBy,
}: IOnSort): void => {
  const isAsc = orderBy === id && order === Order.ASC;
  if (id !== '') {
    setOrder(isAsc ? Order.DESC : Order.ASC);
    setOrderBy?.(id);
  }
};

export const onChangeRowsPerPage = (
  event: React.ChangeEvent<HTMLInputElement>,
  setRowsPerPage: (rowsPerPage: number) => void,
  setPage: (page: number) => void
): void => {
  setRowsPerPage(parseInt(event.target.value, 10));
  setPage(0);
};

export function emptyRows(rowsPerPage: number, arrayLength: number): number {
  return arrayLength < rowsPerPage ? rowsPerPage - arrayLength : 0;
}
