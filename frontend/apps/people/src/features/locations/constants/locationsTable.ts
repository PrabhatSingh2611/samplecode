import { AlignTableCell } from 'wdx';

export enum ELocationsSearchParams {
    RowsPerPage = 'rowsPerPage',
    CurrentPage = 'currentPage',
    Order = 'order',
    OrderBy = 'orderBy',
}

export const ORDER_BY = 'location';
export const TABLE_DEFAULT_ROWS_COUNT = 10;

export const SEARCH_PARAMS_KEYS = [
    ELocationsSearchParams.Order,
    ELocationsSearchParams.OrderBy,
    ELocationsSearchParams.CurrentPage,
    ELocationsSearchParams.RowsPerPage,
];

interface IHeaderCellData {
    id: string;
    align?: AlignTableCell;
    width?: string;
    minWidth?: string;
    label?: string;
    isClickable?: boolean;
}

export const TABLE_HEAD: IHeaderCellData[] = [
    { id: 'location', label: 'Location', align: AlignTableCell.LEFT, isClickable: true },
    { id: 'country', label: 'Country', align: AlignTableCell.LEFT },
    { id: 'action' },
];
