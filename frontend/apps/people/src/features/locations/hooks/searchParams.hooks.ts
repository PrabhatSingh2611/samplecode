import { castToString, Order, useGetUrlSearchParams } from 'wdx';

import {
    SEARCH_PARAMS_KEYS,
    TABLE_DEFAULT_ROWS_COUNT,
} from 'features/locations/constants/locationsTable';

interface IGetLocationsListSearchParamsResult {
    currentPage: number;
    order: Order;
    rowsPerPage: number;
}

export function useGetLocationsListSearchParams(): IGetLocationsListSearchParamsResult {
    const urlSearchParamsValues = useGetUrlSearchParams(SEARCH_PARAMS_KEYS);

    const {
        currentPage,
        order = Order.ASC,
        rowsPerPage = TABLE_DEFAULT_ROWS_COUNT.toString(),
    } = urlSearchParamsValues;

    return {
        currentPage: +castToString(currentPage, '1'),
        order: order as Order,
        rowsPerPage: +rowsPerPage,
    };
}
