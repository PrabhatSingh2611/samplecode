import React, { useState } from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import { WBox, WButton, WTypography } from 'wdx';

import EmployeesList from 'features/employees/components/EmployeesList.component';
import LeaveRequestDrawer from 'features/leave/components/leave-request-drawer/LeaveRequestDrawer.component';
import RequestsPage from 'features/leave/containers/requests-page/RequestsPage.container';
import LocationsPage from 'features/locations/containers/LocationsPage.container';
import PoliciesPage from 'features/policies/containers/policies-page/PoliciesPage.container';
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
                <EmployeesList />
            </Route>
            <Route path={EPeopleRouterLink.Leaves}>
                <RequestsPage />
            </Route>
            <Route path={EPeopleRouterLink.Playbooks}>
                <PageSample title="Playbooks page" />
            </Route>
            <Route path={EPeopleRouterLink.Positions}>
                <PageSample title="Positions page" />
            </Route>
            <Route path={EPeopleRouterLink.Locations}>
                <LocationsPage />
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
    const [isDrawerOpened, setIsDrawerOpened] = useState(false);

    return (
        <WBox>
            <WTypography>{title}</WTypography>
            <WButton onClick={(): void => setIsDrawerOpened(true)}>Request a Leave</WButton>
            <LeaveRequestDrawer isOpened={isDrawerOpened} setIsOpened={setIsDrawerOpened} />
        </WBox>
    );
}

export default Routes;
