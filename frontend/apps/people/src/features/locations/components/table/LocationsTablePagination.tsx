import React, { ChangeEvent } from 'react';

import { useUpdateSearchUrlParam, WBox, WForm, WSwitch, WTable } from 'wdx';

import { DEFAULT_PAGE_NUMBER_API, DEFAULT_PAGE_NUMBER_TABLE } from 'constants/index';
import { ELocationsSearchParams } from 'features/locations/constants/locationsTable';
import { useGetLocationsTableList } from 'features/locations/hooks/locationsTable.hooks';
import { useGetLocationsListSearchParams } from 'features/locations/hooks/searchParams.hooks';

interface ILocationsTablePaginationProps {
    dense: boolean;
    setDense: (dense: boolean) => void;
}

export function LocationsTablePagination({
    dense,
    setDense,
}: ILocationsTablePaginationProps): JSX.Element {
    const updateSearchParam = useUpdateSearchUrlParam();
    const { rowsPerPage } = useGetLocationsListSearchParams();
    const { locationsListQuery } = useGetLocationsTableList();

    const perPage = [5, 10, 25];
    const pageInfo = locationsListQuery.data?.locations.pageInfo;
    const totalItems = locationsListQuery.data?.locations.totalItems;

    const onChangeDense = (event: ChangeEvent<HTMLInputElement>): void => {
        setDense(event.target.checked);
    };

    const onChangePage = (_: unknown, newPage: number): void => {
        updateSearchParam(ELocationsSearchParams.CurrentPage, newPage + DEFAULT_PAGE_NUMBER_API);
    };
    const onChangeRowsPerPage = (event: ChangeEvent<HTMLInputElement>): void => {
        updateSearchParam(ELocationsSearchParams.RowsPerPage, event.target.value);
    };

    const tableCurrentPage =
        pageInfo &&
        (pageInfo.currentPage < 0
            ? DEFAULT_PAGE_NUMBER_TABLE
            : pageInfo.currentPage - DEFAULT_PAGE_NUMBER_API);

    return (
        <WBox sx={{ position: 'relative' }}>
            {!!pageInfo && (
                <WTable.Pagination
                    rowsPerPageOptions={perPage}
                    component="div"
                    count={totalItems}
                    rowsPerPage={rowsPerPage}
                    page={tableCurrentPage}
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
