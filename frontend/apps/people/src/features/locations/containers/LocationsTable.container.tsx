import React, { useMemo, useState } from 'react';

import { useAddQueryParamsToUrl, WCard } from 'wdx';

import { LocationsTableBody } from 'features/locations/components/table/LocationsTableBody';
import { LocationsTablePagination } from 'features/locations/components/table/LocationsTablePagination';
import { useGetLocationsListSearchParams } from 'features/locations/hooks/searchParams.hooks';

export function LocationsTableContainer(): JSX.Element {
    const [dense, setDense] = useState<boolean>(false);
    const { currentPage, order, rowsPerPage } = useGetLocationsListSearchParams();

    const urlSearchParams = useMemo(() => {
        return {
            order,
            currentPage,
            rowsPerPage,
        };
    }, [currentPage, order, rowsPerPage]);

    useAddQueryParamsToUrl(urlSearchParams);

    return (
        <WCard>
            <LocationsTableBody dense={dense} />
            <LocationsTablePagination dense={dense} setDense={setDense} />
        </WCard>
    );
}
