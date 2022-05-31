import React, { useMemo, useState } from 'react';

import { WCard, useAddQueryParamsToUrl } from 'wdx';

import { AssetsTableBody } from 'features/assets-table/components/table/AssetsTableBody';
import { AssetsTablePagination } from 'features/assets-table/components/table/AssetsTablePagination';
import UserTableToolbar from 'features/assets-table/components/table/UserTableToolbar';
import { useGetAssetsListSearchParams } from 'features/assets-table/hooks/searcParams.hooks';

export function AssetsTableContainer(): JSX.Element {
    const [dense, setDense] = useState<boolean>(false);
    const { currentPage, order, rowsPerPage, searchValue } = useGetAssetsListSearchParams();

    const urlSearchParams = useMemo(() => {
        return {
            order,
            currentPage,
            rowsPerPage,
            searchValue,
        };
    }, [order, searchValue, currentPage, rowsPerPage]);

    useAddQueryParamsToUrl(urlSearchParams);

    return (
        <WCard>
            <UserTableToolbar />
            <AssetsTableBody dense={dense} />
            <AssetsTablePagination dense={dense} setDense={setDense} />
        </WCard>
    );
}
