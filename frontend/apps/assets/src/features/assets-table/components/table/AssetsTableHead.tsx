import React from 'react';

import { IHeaderCellData, Order, useUpdateSearchUrlParam, WTableHead } from 'wdx';

import { EAssetsSearchParams, ORDER_BY, TABLE_HEAD } from 'const/assets-table';
import { useGetAssetsListSearchParams } from 'features/assets-table/hooks/searcParams.hooks';

export function AssetsTableHead(): JSX.Element {
    const { order } = useGetAssetsListSearchParams();
    const updateSearchParam = useUpdateSearchUrlParam();

    const onOrderChange = (value: IHeaderCellData): void => {
        const isAsc = ORDER_BY === value.id && order === Order.ASC;
        const newOrder = isAsc ? Order.DESC : Order.ASC;

        updateSearchParam(EAssetsSearchParams.Order, newOrder);
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
