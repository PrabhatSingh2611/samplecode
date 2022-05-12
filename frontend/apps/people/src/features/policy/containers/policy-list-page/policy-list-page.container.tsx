import { WPage, WTypography } from 'wdx';

import PolicyPageEmptyState from 'features/policy/components/policy-page-empty-state/policy-page-empty-state.component';

export default function PolicyPage(): JSX.Element {
    return (
        <WPage title="Policy">
            <WTypography variant="h4">Policy</WTypography>
            <PolicyPageEmptyState />
        </WPage>
    );
}
