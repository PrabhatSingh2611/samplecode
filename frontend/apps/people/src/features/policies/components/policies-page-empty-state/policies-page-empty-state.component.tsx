import { WButton, WEmptyState } from 'wdx';

import { useIsAddPolicyDrawerOpened } from 'features/policies/hooks/api-policies.hooks';

export default function PolicyPageEmptyState(): JSX.Element {
    const [, setIsAddPolicyDrawerOpened] = useIsAddPolicyDrawerOpened();

    return (
        <WEmptyState
            title="You have no documents"
            subtitle="Use the “Create” button to create the policy"
            actions={
                <WButton
                    variant="contained"
                    size="large"
                    sx={{ minWidth: 254 }}
                    onClick={(): void => setIsAddPolicyDrawerOpened(true)}
                >
                    Create
                </WButton>
            }
            sx={{ height: '65vh' }}
        />
    );
}
