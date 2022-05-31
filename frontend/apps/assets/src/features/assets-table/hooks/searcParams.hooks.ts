import { castToString, Order, useGetUrlSearchParams } from 'wdx';

import { SEARCH_PARAMS_KEYS, TABLE_DEFAULT_ROWS_COUNT } from 'const/assets-table';

interface IGetAssetsListSearchParamsResult {
    currentPage: number;
    order: Order;
    rowsPerPage: number;
    searchValue: string;
}

export function useGetAssetsListSearchParams(): IGetAssetsListSearchParamsResult {
    const urlSearchParamsValues = useGetUrlSearchParams(SEARCH_PARAMS_KEYS);

    const {
        currentPage,
        order = Order.DESC,
        rowsPerPage = TABLE_DEFAULT_ROWS_COUNT.toString(),
        searchValue,
    } = urlSearchParamsValues;

    return {
        currentPage: +castToString(currentPage, '0'),
        order: order as Order,
        rowsPerPage: +rowsPerPage,
        searchValue: castToString(searchValue),
    };
}
