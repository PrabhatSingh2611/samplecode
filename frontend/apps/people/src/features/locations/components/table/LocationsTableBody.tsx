import React, { useMemo } from 'react';

import { EFetchPolicy, WScrollbarContainer, WTable } from 'wdx';

import { LocationsTableHead } from 'features/locations/components/table/LocationsTableHead';
import LocationsTableRow from 'features/locations/components/table/LocationsTableRow';
import { useGetLocationsTableList } from 'features/locations/hooks/locationsTable.hooks';
import { useGetLocationsListSearchParams } from 'features/locations/hooks/searchParams.hooks';

interface ILocationsTableBodyProps {
    dense: boolean;
}

export function LocationsTableBody({ dense }: ILocationsTableBodyProps): JSX.Element {
    const { rowsPerPage } = useGetLocationsListSearchParams();
    const { locations, locationsListQuery } = useGetLocationsTableList(
        EFetchPolicy.CACHE_AND_NETWORK,
    );

    const { error, loading: isLoading } = locationsListQuery;

    const isNotFound = Boolean(!!locations?.length || error);

    const minDenseHeight = 52;
    const maxDenseHeight = 72;
    const denseHeight = dense ? minDenseHeight : maxDenseHeight;

    const tableData = useMemo(
        () => (
            <>
                {locations?.map((row) => (
                    <LocationsTableRow
                        key={row.id}
                        row={row}
                        onDeleteRow={(): void => handleDeleteRow(row.id)}
                        onEditRow={(): void => handleEditRow(row.id)}
                        onViewDetails={(): void => handleViewDetails(row.id)}
                    />
                ))}
            </>
        ),
        [locations],
    );

    const tableSkeleton = useMemo(
        () => (
            <>
                {[...Array(rowsPerPage)].map((_, index) => (
                    <WTable.Skeleton key={index} sx={{ height: denseHeight }} />
                ))}
            </>
        ),
        [denseHeight, rowsPerPage],
    );

    // TODO: Add logic for delete row (TD)
    const handleDeleteRow = (id: string): void => {
        console.log(id);
    };

    // TODO: Add logic for edit row (TD)
    const handleEditRow = (id: string): void => {
        console.log(id);
    };

    // TODO: Add logic for edit row (TD)
    const handleViewDetails = (id: string): void => {
        console.log(id);
    };

    function emptyRows(perPage: number, arrayLength: number): number {
        return arrayLength < perPage ? perPage - arrayLength : 0;
    }

    const emptyRowsCount = !!locations?.length && emptyRows(rowsPerPage, locations.length);
    const showTableNoData = !isLoading && !isNotFound;

    return (
        <WScrollbarContainer>
            <WTable.Container sx={{ position: 'relative', minWidth: 800, pt: 1 }}>
                <WTable size={dense ? 'small' : 'medium'}>
                    <LocationsTableHead />

                    <WTable.Body>
                        {!isLoading ? tableData : tableSkeleton}

                        {!!emptyRowsCount && (
                            <WTable.EmptyRows height={denseHeight} emptyRows={emptyRowsCount} />
                        )}

                        {showTableNoData ? <WTable.NoData /> : null}
                    </WTable.Body>
                </WTable>
            </WTable.Container>
        </WScrollbarContainer>
    );
}
