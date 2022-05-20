import { WPage, WSubHeader, WButton } from 'wdx';

import AddPolicyDrawer from 'features/policies/components/add-policy-drawer/AddPolicyDrawer';
import PolicyPageEmptyState from 'features/policies/components/policies-page-empty-state/policies-page-empty-state.component';
import { useIsAddPolicyDrawerOpened } from 'features/policies/hooks/api-policies.hooks';

export default function PoliciesPage(): JSX.Element {
    const [, setIsAddPolicyDrawerOpened] = useIsAddPolicyDrawerOpened();

    return (
        <WPage title="Policies">
            <WSubHeader
                title="Policies"
                actions={
                    <WButton
                        variant="contained"
                        size="large"
                        onClick={(): void => {
                            setIsAddPolicyDrawerOpened(true);
                        }}
                    >
                        Add Policy
                    </WButton>
                }
            />
            <PolicyPageEmptyState />
            <AddPolicyDrawer />
        </WPage>
    );
}
