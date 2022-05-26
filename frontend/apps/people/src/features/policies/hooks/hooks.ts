import { castToString, Order, useGetUrlSearchParams } from 'wdx';

import { POLICIES_TABLE_DEFAULT_ROWS_COUNT, POLICIES_URL_SEARCH_PARAMS_KEYS } from '../constants';

interface IGetPoliciesListPageSearchParamsResult {
    currentPage: number;
    order: Order;
    policiesPerPage: number;
    searchValue: string;
}

export function useGetPoliciesListPageSearchParams(): IGetPoliciesListPageSearchParamsResult {
    const urlSearchParamsValues = useGetUrlSearchParams(POLICIES_URL_SEARCH_PARAMS_KEYS);

    const {
        currentPage,
        order = Order.DESC,
        policiesPerPage = POLICIES_TABLE_DEFAULT_ROWS_COUNT.toString(),
        searchValue,
    } = urlSearchParamsValues;

    return {
        currentPage: +castToString(currentPage, '0'),
        order: order as Order,
        policiesPerPage: +policiesPerPage,
        searchValue: castToString(searchValue),
    };
}
