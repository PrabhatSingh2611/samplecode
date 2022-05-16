import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import PoliciesPage from 'features/policies/containers/policies-page/policies-page.container';
import { EPeopleRouterLink } from 'models/router/people-router-link';

function Routes(): JSX.Element {
    return (
        <Switch>
            <Route path={EPeopleRouterLink.Me}>
                <PageSample title="Me page" />
            </Route>
            <Route path={EPeopleRouterLink.Policies}>
                <PoliciesPage />
            </Route>
            <Route path={EPeopleRouterLink.Employees}>
                <PageSample title="Employees page" />
            </Route>
            <Route path={EPeopleRouterLink.Leaves}>
                <PageSample title="Leaves page" />
            </Route>
            <Route path={EPeopleRouterLink.Playbooks}>
                <PageSample title="Playbooks" />
            </Route>
            <Route exact={true} path="/">
                <Redirect to={EPeopleRouterLink.Me} />
            </Route>
        </Switch>
    );
}

// TODO: Remove after adding all pages
function PageSample({ title }: { title: string }): JSX.Element {
    return (
        <div>
            <h1>{title}</h1>
        </div>
    );
}

export default Routes;
