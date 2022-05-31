import React, { ChangeEvent } from 'react';

import { WForm, WTable, WBox, WSwitch, useUpdateSearchUrlParam } from 'wdx';

import { EAssetsSearchParams } from 'const/assets-table';
import { useGetAssetsTableList } from 'features/assets-table/hooks/assetsTable.hooks';
import { useGetAssetsListSearchParams } from 'features/assets-table/hooks/searcParams.hooks';

interface IAssetsTablePaginationProps {
    dense: boolean;
    setDense: (dense: boolean) => void;
}

export function AssetsTablePagination({
    dense,
    setDense,
}: IAssetsTablePaginationProps): JSX.Element {
    const updateSearchParam = useUpdateSearchUrlParam();
    const { rowsPerPage } = useGetAssetsListSearchParams();
    const { data } = useGetAssetsTableList();

    const perPage = [5, 10, 25];
    const pageInfo = data?.assets.pageInfo;
    const totalItems = data?.assets.totalItems;

    const onChangeDense = (event: ChangeEvent<HTMLInputElement>): void => {
        setDense(event.target.checked);
    };

    const onChangePage = (_: unknown, newPage: number): void => {
        updateSearchParam(EAssetsSearchParams.CurrentPage, newPage);
    };
    const onChangeRowsPerPage = (event: ChangeEvent<HTMLInputElement>): void => {
        updateSearchParam(EAssetsSearchParams.RowsPerPage, event.target.value);
    };

    return (
        <WBox sx={{ position: 'relative' }}>
            {!!pageInfo && (
                <WTable.Pagination
                    rowsPerPageOptions={perPage}
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
    );
}
