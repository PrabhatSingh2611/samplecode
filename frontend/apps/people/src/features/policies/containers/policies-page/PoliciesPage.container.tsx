import React from 'react';

import { useGetUrlSearchParams, WButton, WPage, WSubHeader } from 'wdx';

import AddPolicyDrawer from 'features/policies/components/add-policy-drawer/AddPolicyDrawer';
import PolicyPageEmptyState from 'features/policies/components/policies-page-empty-state/policies-page-empty-state.component';
import { EPoliciesPageSearchParams } from 'features/policies/components/policies-table-view/PoliciesTableView.component';
import PoliciesListContent from 'features/policies/containers/policies-list-content/PoliciesListContent.container';
import {
    useGetPolicies,
    useGetTotalPolicies,
    useIsAddPolicyDrawerOpened,
} from 'features/policies/hooks/api-policies.hooks';

export default function PoliciesPage(): JSX.Element {
    const { searchValue } = useGetUrlSearchParams([EPoliciesPageSearchParams.SearchValue]);
    const [totalPoliciesCount, getTotalPoliciesQuery] = useGetTotalPolicies();
    const [, getPoliciesQuery] = useGetPolicies();
    const [, setIsAddPolicyDrawerOpened] = useIsAddPolicyDrawerOpened();

    const shouldShowEmptyState =
        !totalPoliciesCount && !getTotalPoliciesQuery.loading && !searchValue?.length;

    const refetchPolicies = (): void => {
        getPoliciesQuery.refetch();
        getTotalPoliciesQuery.refetch();
    };

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
            <AddPolicyDrawer refetchPolicies={refetchPolicies} />
            {shouldShowEmptyState ? (
                <PolicyPageEmptyState />
            ) : (
                <PoliciesListContent
                    totalPoliciesCount={totalPoliciesCount}
                    refetchPoliciesTotal={getTotalPoliciesQuery.refetch}
                />
            )}
        </WPage>
    );
}
