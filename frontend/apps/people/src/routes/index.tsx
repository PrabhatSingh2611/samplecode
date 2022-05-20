import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import { WBox, WTypography } from 'wdx';

import EmployeesList from 'features/employees/components/EmployeesList.component';
import AuthorizationBtn from 'features/me/components/authBtn';
import PoliciesPage from 'features/policies/containers/policies-page/policies-page.container';
import { EPeopleRouterLink } from 'models/router/people-router-link';

function Routes(): JSX.Element {
    return (
        <Switch>
            <Route path={EPeopleRouterLink.Me}>
                <AuthorizationBtn />
            </Route>
            <Route path={EPeopleRouterLink.Policies}>
                <PoliciesPage />
            </Route>
            <Route path={EPeopleRouterLink.Employees}>
                <EmployeesList />
            </Route>
            <Route path={EPeopleRouterLink.Leaves}>
                <PageSample title="Leaves page" />
            </Route>
            <Route path={EPeopleRouterLink.Playbooks}>
                <PageSample title="Playbooks page" />
            </Route>
            <Route path={EPeopleRouterLink.Positions}>
                <PageSample title="Positions page" />
            </Route>
            <Route path={EPeopleRouterLink.Locations}>
                <PageSample title="Locations page" />
            </Route>
            <Route path={EPeopleRouterLink.Admins}>
                <PageSample title="Admins page" />
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
        <WBox>
            <WTypography>{title}</WTypography>
        </WBox>
    );
}

export default Routes;
