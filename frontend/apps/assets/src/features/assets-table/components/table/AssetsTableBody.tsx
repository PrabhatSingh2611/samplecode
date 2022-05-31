import React from 'react';

import { WScrollbarContainer, WTable } from 'wdx';

import AssetTableRow from 'features/assets-table/components/table/AssetTableRow';
import { AssetsTableHead } from 'features/assets-table/components/table/AssetsTableHead';
import TableNoData from 'features/assets-table/components/table/TableNoData';
import TableSkeleton from 'features/assets-table/components/table/TableSkeleton';
import { useGetAssetsTableList } from 'features/assets-table/hooks/assetsTable.hooks';
import { useGetAssetsListSearchParams } from 'features/assets-table/hooks/searcParams.hooks';

interface IAssetsTableBodyProps {
    dense: boolean;
}

export function AssetsTableBody({ dense }: IAssetsTableBodyProps): JSX.Element {
    const { rowsPerPage } = useGetAssetsListSearchParams();
    const { data, isLoading, error } = useGetAssetsTableList();

    const tableData = data?.assets.items;
    const isNotFound = !!tableData?.length || error;

    const minDenseHeight = 52;
    const maxDenseHeight = 72;
    const denseHeight = dense ? minDenseHeight : maxDenseHeight;

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

    const emptyRowsCount = !!tableData?.length && emptyRows(rowsPerPage, tableData.length);

    return (
        <WScrollbarContainer>
            <WTable.Container sx={{ position: 'relative', minWidth: 800 }}>
                <WTable size={dense ? 'small' : 'medium'}>
                    <AssetsTableHead />

                    <WTable.Body>
                        {!isLoading
                            ? tableData?.map((row) => (
                                  <AssetTableRow
                                      key={row.id}
                                      row={row}
                                      onDeleteRow={(): void => handleDeleteRow(row.id)}
                                      onEditRow={(): void => handleEditRow(row.id)}
                                  />
                              ))
                            : [...Array(rowsPerPage)].map((_, index) => (
                                  <TableSkeleton key={index} sx={{ height: denseHeight }} />
                              ))}

                        {!!emptyRowsCount && (
                            <WTable.EmptyRows height={denseHeight} emptyRows={emptyRowsCount} />
                        )}

                        {!isLoading ? <TableNoData isNotFound={!isNotFound} /> : null}
                    </WTable.Body>
                </WTable>
            </WTable.Container>
        </WScrollbarContainer>
    );
}
