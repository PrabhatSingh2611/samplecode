import React from 'react';
import { Route, Switch } from 'react-router-dom';

import AssetsPage from 'features/assets-table/containers/AssetsPage.container';
import { EAssetsRouterLink } from 'models/router/assets-router-link';

function Routes(): JSX.Element {
    return (
        <Switch>
            <Route path={EAssetsRouterLink.Assets}>
                <AssetsPage />
            </Route>
        </Switch>
    );
}

export default Routes;
