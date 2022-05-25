import React from 'react';

import { PoliciesListTableView } from 'features/policies/components/policies-table-view/PoliciesTableView.component';
import { PolicyForPolicyListFragment } from 'features/policies/graphql/queries/getPoliciesForPoliciesList.generated';

interface IPolicyListContentProps {
    tableData: PolicyForPolicyListFragment[];
    totalPageCount: number;
    selected: string[];
    onSelectRow: (selectedId: string) => void;
    selectAllRows: (selectedIds: string[]) => void;
    onDeletePolicies: () => void;
}

//Need  this Component because this page would also have grid view (VS)

export default function PoliciesListContent(props: IPolicyListContentProps): JSX.Element {
    return <PoliciesListTableView {...props} />;
}
