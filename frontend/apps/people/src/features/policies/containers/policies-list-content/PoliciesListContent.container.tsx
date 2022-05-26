import React from 'react';

import { castToArray, EFetchPolicy } from 'wdx';

import { PoliciesListTableView } from 'features/policies/components/policies-table-view/PoliciesTableView.component';
import { useGetPolicies } from 'features/policies/hooks/api-policies.hooks';

//Need  this Component because this page would also have grid view (VS)

interface IPoliciesListContentProps {
    totalPoliciesCount: number;
    refetchPoliciesTotal: () => void;
}

export default function PoliciesListContent({
    totalPoliciesCount,
    refetchPoliciesTotal,
}: IPoliciesListContentProps): JSX.Element {
    const [policiesConnection, getPoliciesQuery] = useGetPolicies(EFetchPolicy.CACHE_AND_NETWORK);

    const refetchPoliciesData = (): void => {
        getPoliciesQuery.refetch();
        refetchPoliciesTotal();
    };
    const policies = castToArray(policiesConnection?.items);

    return (
        <PoliciesListTableView
            tableData={policies}
            refetchPoliciesData={refetchPoliciesData}
            totalPoliciesCount={totalPoliciesCount}
        />
    );
}
