import React, { useMemo } from 'react';

import { WScrollbarContainer, WTable } from 'wdx';

import AssetTableRow from 'features/assets-table/components/table/AssetTableRow';
import { AssetsTableHead } from 'features/assets-table/components/table/AssetsTableHead';
import TableSkeletonChildren from 'features/assets-table/components/table/TableSkeletonChildren';
import { useGetAssetsTableList } from 'features/assets-table/hooks/assetsTable.hooks';
import { useGetAssetsListSearchParams } from 'features/assets-table/hooks/searcParams.hooks';

interface IAssetsTableBodyProps {
    dense: boolean;
}

export function AssetsTableBody({ dense }: IAssetsTableBodyProps): JSX.Element {
    const { rowsPerPage } = useGetAssetsListSearchParams();
    const { data, isLoading, error } = useGetAssetsTableList();

    const tableDataArr = data?.assets.items;
    const isNotFound = !!tableDataArr?.length || error;

    const minDenseHeight = 52;
    const maxDenseHeight = 72;
    const denseHeight = dense ? minDenseHeight : maxDenseHeight;

    const tableData = useMemo(
        () => (
            <>
                {tableDataArr?.map((row) => (
                    <AssetTableRow
                        key={row.id}
                        row={row}
                        onDeleteRow={(): void => handleDeleteRow(row.id)}
                        onEditRow={(): void => handleEditRow(row.id)}
                    />
                ))}
            </>
        ),
        [tableDataArr],
    );

    const tableSkeleton = useMemo(
        () => (
            <>
                {[...Array(rowsPerPage)].map((_, index) => (
                    <WTable.Skeleton key={index} sx={{ height: denseHeight }}>
                        {TableSkeletonChildren()}
                    </WTable.Skeleton>
                ))}
            </>
        ),
        [denseHeight, rowsPerPage],
    );

    // TODO: Add logic for delete row (TK)
    const handleDeleteRow = (id: string): void => {
        console.log(id);
    };

    // TODO: Add logic for edit row (TK)
    const handleEditRow = (id: string): void => {
        console.log(id);
    };

    function emptyRows(perPage: number, arrayLength: number): number {
        return arrayLength < perPage ? perPage - arrayLength : 0;
    }

    const emptyRowsCount = !!tableDataArr?.length && emptyRows(rowsPerPage, tableDataArr.length);
    const showTableNoData = !isLoading && !isNotFound;

    return (
        <WScrollbarContainer>
            <WTable.Container sx={{ position: 'relative', minWidth: 800 }}>
                <WTable size={dense ? 'small' : 'medium'}>
                    <AssetsTableHead />

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
