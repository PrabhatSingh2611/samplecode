import React, { useState } from 'react';

import { Order, WBox, WCard, WForm, WSwitch, WTable, WTableHead, WScrollbarContainer } from 'wdx';

import { TABLE_HEAD } from 'const/AssetsTable.const';
import AssetTableRow from 'features/assets-table/components/table/AssetTableRow';
import TableNoData from 'features/assets-table/components/table/TableNoData';
import TableSkeleton from 'features/assets-table/components/table/TableSkeleton';
import UserTableToolbar from 'features/assets-table/components/table/UserTableToolbar';
import { useGetAssetsTableList } from 'features/assets-table/hooks/assetsTable.hooks';
import useTable, { emptyRows } from 'features/assets-table/hooks/useTable';
import { AssetSortEnum } from 'graphql-generated-types/types';

export function AssetsTableContainer(): JSX.Element {
    const {
        dense,
        page,
        order,
        orderBy,
        rowsPerPage,
        setPage,
        onSort,
        onChangeDense,
        onChangePage,
        onChangeRowsPerPage,
    } = useTable();

    const [filterName, setFilterName] = useState('');

    const handleFilterName = (selectedFilterName: string): void => {
        setFilterName(selectedFilterName);
        setPage(0);
    };

    // TODO: Add logic for delete row (TK)
    const handleDeleteRow = (id: string): void => {
        console.log(id);
    };

    // TODO: Add logic for edit row (TK)
    const handleEditRow = (id: string): void => {
        console.log(id);
    };

    const waybillOrder =
        order === Order.DESC ? AssetSortEnum.WaybillDateDesc : AssetSortEnum.WaybillDateAsc;

    const { data, isLoading, error } = useGetAssetsTableList({
        itemsPerPage: rowsPerPage,
        pageNumber: page,
        sort: [waybillOrder],
    });

    const tableData = data?.assets.items;
    const pageInfo = data?.assets.pageInfo;
    const totalItems = data?.assets.totalItems;

    const denseHeight = dense ? 52 : 72;
    const isNotFound = !!tableData?.length || error;
    const emptyRowsCount = !!tableData?.length && emptyRows(rowsPerPage, tableData.length);

    return (
        <WCard>
            <UserTableToolbar filterName={filterName} onFilterName={handleFilterName} />

            <WScrollbarContainer>
                <WTable.Container sx={{ position: 'relative', minWidth: 800 }}>
                    <WTable size={dense ? 'small' : 'medium'}>
                        <WTableHead
                            order={order}
                            orderBy={orderBy}
                            headerData={TABLE_HEAD}
                            onSortHandler={onSort}
                        />

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

            <WBox sx={{ position: 'relative' }}>
                {!!pageInfo && !!totalItems && (
                    <WTable.Pagination
                        rowsPerPageOptions={[5, 10, 25]}
                        component="div"
                        count={totalItems}
                        rowsPerPage={rowsPerPage}
                        page={pageInfo.currentPage}
                        onPageChange={onChangePage}
                        onRowsPerPageChange={onChangeRowsPerPage}
                    />
                )}

                <WForm.ControlLabel
                    control={<WSwitch checked={dense} onChange={onChangeDense} />}
                    label="Dense"
                    sx={{ position: { md: 'absolute' }, top: 0, py: 1.5, px: 3 }}
                />
            </WBox>
        </WCard>
    );
}
