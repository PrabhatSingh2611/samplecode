import { WPage } from 'wdx';

import RequestsList from 'features/leave/components/requests-list/RequestsList.component';

export default function RequestsPage(): JSX.Element {
    return (
        <WPage title="Requests">
            <RequestsList />
        </WPage>
    );
}
