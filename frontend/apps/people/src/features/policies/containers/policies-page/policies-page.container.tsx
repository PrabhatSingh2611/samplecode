import { WPage, WSubHeader, WButton } from 'wdx';

import PolicyPageEmptyState from 'features/policies/components/policies-page-empty-state/policies-page-empty-state.component';

export default function PoliciesPage(): JSX.Element {
    return (
        <WPage title="Policies">
            <WSubHeader
                title="Policies"
                actions={
                    <WButton variant="contained" size="large">
                        Create
                    </WButton>
                }
            />
            <PolicyPageEmptyState />
        </WPage>
    );
}
