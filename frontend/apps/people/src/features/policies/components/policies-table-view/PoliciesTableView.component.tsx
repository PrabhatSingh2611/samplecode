import React, { useMemo } from 'react';

import { WBaseTable, WTableToolBar, useUpdateSearchUrlParam, useAddQueryParamsToUrl } from 'wdx';

import { PolicyListTableRow } from 'features/policies/components/policies-table-row/PoliciesTableRow.component';
import { PoliciesTableSelectActions } from 'features/policies/components/policies-table-select-actions/PoliciesTableSelectActions.component';
import { POLICY_TABLE_HEADER_DATA, POLICIES_ORDER_BY } from 'features/policies/constants';
import { PolicyForPolicyListFragment } from 'features/policies/graphql/queries/getPoliciesForPoliciesList.generated';
import { useGetPoliciesListPageSearchParams } from 'features/policies/hooks/hooks';

export enum EPoliciesPageSearchParams {
    PoliciesPerPage = 'policiesPerPage',
    CurrentPage = 'currentPage',
    SearchValue = 'searchValue',
    Order = 'order',
}

interface IPoliciesListTableViewProps {
    tableData: PolicyForPolicyListFragment[];
    totalPageCount: number;
    selected: string[];
    onSelectRow: (selectedId: string) => void;
    selectAllRows: (selectedId: string[]) => void;
    onDeletePolicies: () => void;
}

export function PoliciesListTableView({
    tableData,
    totalPageCount,
    selected,
    onSelectRow,
    selectAllRows,
    onDeletePolicies,
}: IPoliciesListTableViewProps): JSX.Element {
    const { currentPage, order, policiesPerPage, searchValue } =
        useGetPoliciesListPageSearchParams();

    const updateSearchParam = useUpdateSearchUrlParam();

    const urlSearchParams = useMemo(() => {
        return {
            order,
            currentPage,
            policiesPerPage,
            searchValue,
        };
    }, [order, searchValue, currentPage, policiesPerPage]);
    useAddQueryParamsToUrl(urlSearchParams);
    const onSearchChange = (searchValue: string): void => {
        updateSearchParam(EPoliciesPageSearchParams.SearchValue, searchValue);
        updateSearchParam(EPoliciesPageSearchParams.CurrentPage, 0);
    };

    const onOrderChange = (order: string): void => {
        updateSearchParam(EPoliciesPageSearchParams.Order, order);
    };

    return (
        <WBaseTable
            headerData={POLICY_TABLE_HEADER_DATA}
            bodyData={tableData}
            withFooter={true}
            totalPageCount={totalPageCount}
            currentPage={currentPage}
            setCurrentPage={(page: number): void => {
                updateSearchParam(EPoliciesPageSearchParams.CurrentPage, page);
            }}
            rowsPerPage={policiesPerPage}
            setRowsPerPage={(perPage: number): void =>
                updateSearchParam(EPoliciesPageSearchParams.PoliciesPerPage, perPage)
            }
            selected={selected}
            toolbarElements={
                <WTableToolBar.SearchBar
                    onSearchChange={onSearchChange}
                    searchValue={searchValue}
                />
            }
            orderBy={POLICIES_ORDER_BY}
            order={order}
            setOrder={onOrderChange}
            renderBodyRow={(rowData: PolicyForPolicyListFragment): JSX.Element => (
                <PolicyListTableRow
                    rowData={rowData}
                    selected={selected.includes(rowData.id)}
                    onSelectRow={onSelectRow}
                />
            )}
            setSelected={selectAllRows}
            selectActions={<PoliciesTableSelectActions onDeletePolicies={onDeletePolicies} />}
            emptyRowsHeight={96}
        />
    );
}
