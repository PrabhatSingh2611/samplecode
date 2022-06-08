import React from 'react';

import { Order, useUpdateSearchUrlParam, WTableHead } from 'wdx';

import {
    ELocationsSearchParams,
    ORDER_BY,
    TABLE_HEAD,
} from 'features/locations/constants/locationsTable';
import { useGetLocationsListSearchParams } from 'features/locations/hooks/searchParams.hooks';

export function LocationsTableHead(): JSX.Element {
    const { order } = useGetLocationsListSearchParams();
    const updateSearchParam = useUpdateSearchUrlParam();

    const onOrderChange = (): void => {
        const isAsc = order === Order.ASC;
        const newOrder = isAsc ? Order.DESC : Order.ASC;

        updateSearchParam(ELocationsSearchParams.Order, newOrder);
    };

    return (
        <WTableHead
            order={order}
            orderBy={ORDER_BY}
            headerData={TABLE_HEAD}
            onSortHandler={onOrderChange}
        />
    );
}
