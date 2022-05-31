import React from 'react';
import { Route, Switch } from 'react-router-dom';

import AssetsPage from 'features/assets-table/containers/AssetsPage.container';
import { EAssetsRouterLink } from 'models/router/assets-router-link';

function Routes(): JSX.Element {
    return (
        <Switch>
            <Route path={EAssetsRouterLink.AssetsList}>
                <AssetsPage />
            </Route>
            <Route path={EAssetsRouterLink.Types}>
                <h2>Asset types</h2>
            </Route>
        </Switch>
    );
}

export default Routes;
