import React from 'react';

import { WButton, WPage } from 'wdx';

import AssetsHeader from 'features/assets-table/components/AssetsHeader';
import { AssetsTableContainer } from 'features/assets-table/containers/AssetsTable.container';

export default function AssetsPage(): JSX.Element {
    return (
        <WPage title="Assets">
            <AssetsHeader heading="Assets" action={<WButton variant="contained">Create</WButton>} />
            <AssetsTableContainer />
        </WPage>
    );
}
