import { WPage, WTypography } from 'wdx';

import PolicyPageEmptyState from 'features/policies/components/policies-page-empty-state/policies-page-empty-state.component';

export default function PoliciesPage(): JSX.Element {
    return (
        <WPage title="Policies">
            <WTypography variant="h4">Policies</WTypography>
            <PolicyPageEmptyState />
        </WPage>
    );
}
