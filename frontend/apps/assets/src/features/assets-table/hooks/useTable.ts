import { useState, ChangeEvent } from 'react';

import { IHeaderCellData, Order } from 'wdx';

interface IUseTableResult {
    dense: boolean;
    page: number;
    setPage: (value: number) => void;
    rowsPerPage: number;
    order: Order;
    orderBy: string;
    onSort: (value: IHeaderCellData) => void;
    onChangePage: (event: unknown, newPage: number) => void;
    onChangeRowsPerPage: (event: ChangeEvent<HTMLInputElement>) => void;
    onChangeDense: (event: ChangeEvent<HTMLInputElement>) => void;
}

export default function useTable(): IUseTableResult {
    const [dense, setDense] = useState(false);
    const [orderBy, setOrderBy] = useState('dateOfWaybill');
    const [order, setOrder] = useState<Order>(Order.DESC);
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(5);

    const onSort = (value: IHeaderCellData): void => {
        const isAsc = orderBy === value.id && order === Order.ASC;
        if (value.id !== '') {
            setOrder(isAsc ? Order.DESC : Order.ASC);
            setOrderBy(value.id);
        }
    };

    const onChangePage = (_: unknown, newPage: number): void => {
        setPage(newPage);
    };

    const onChangeRowsPerPage = (event: ChangeEvent<HTMLInputElement>): void => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const onChangeDense = (event: ChangeEvent<HTMLInputElement>): void => {
        setDense(event.target.checked);
    };

    return {
        dense,
        order,
        page,
        setPage,
        orderBy,
        rowsPerPage,
        onSort,
        onChangePage,
        onChangeDense,
        onChangeRowsPerPage,
    };
}

export function emptyRows(rowsPerPage: number, arrayLength: number): number {
    return arrayLength < rowsPerPage ? rowsPerPage - arrayLength : 0;
}
