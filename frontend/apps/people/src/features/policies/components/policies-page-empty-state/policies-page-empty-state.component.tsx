import { WButton, WEmptyState } from 'wdx';

export default function PolicyPageEmptyState(): JSX.Element {
    return (
        <WEmptyState
            title="You have no documents"
            subtitle="Use the “Create” button to create the policy"
            actions={
                <WButton variant="contained" size="large" sx={{ minWidth: 254 }}>
                    Create
                </WButton>
            }
            sx={{ height: '65vh' }}
        />
    );
}
