import React, { useState } from 'react';

import {
    castToArray,
    castToInt,
    WPage,
    onSelectRow,
    WSubHeader,
    WButton,
    useGetUrlSearchParams,
} from 'wdx';

import AddPolicyDrawer from 'features/policies/components/add-policy-drawer/AddPolicyDrawer';
import PoliciesListContent from 'features/policies/components/policies-list-content/PoliciesListContent.component';
import PolicyPageEmptyState from 'features/policies/components/policies-page-empty-state/policies-page-empty-state.component';
import { EPoliciesPageSearchParams } from 'features/policies/components/policies-table-view/PoliciesTableView.component';
import {
    useDeletePolicies,
    useGetPolicies,
    useIsAddPolicyDrawerOpened,
} from 'features/policies/hooks/api-policies.hooks';

export default function PoliciesPage(): JSX.Element {
    const { searchValue } = useGetUrlSearchParams([EPoliciesPageSearchParams.SearchValue]);
    const [, setIsAddPolicyDrawerOpened] = useIsAddPolicyDrawerOpened();
    const [selected, setSelected] = useState<string[]>([]);

    const [policiesConnection, getPoliciesQuery] = useGetPolicies();

    const [deletePolicies] = useDeletePolicies({
        onCompleted: () => getPoliciesQuery.refetch(),
    });
    const onDeletePolicies = (): void => {
        deletePolicies(selected);
        setSelected([]);
    };

    const policies = castToArray(policiesConnection?.items);

    const shouldShowEmptyState =
        !policiesConnection?.totalItems && !getPoliciesQuery.loading && !searchValue?.length;

    const subHeaderActions = !shouldShowEmptyState ? (
        <WButton
            variant="contained"
            size="large"
            onClick={(): void => {
                setIsAddPolicyDrawerOpened(true);
            }}
        >
            Add Policy
        </WButton>
    ) : undefined;

    return (
        <WPage title="Policies">
            <WSubHeader title="Policies" actions={subHeaderActions} />
            <AddPolicyDrawer refetchPolicies={getPoliciesQuery.refetch} />
            {shouldShowEmptyState ? (
                <PolicyPageEmptyState />
            ) : (
                <PoliciesListContent
                    tableData={policies}
                    totalPageCount={castToInt(policiesConnection?.totalItems)}
                    selected={selected}
                    onSelectRow={(rowId: string): void =>
                        onSelectRow({ id: rowId, setSelected, selected })
                    }
                    onDeletePolicies={onDeletePolicies}
                    selectAllRows={setSelected}
                />
            )}
        </WPage>
    );
}
