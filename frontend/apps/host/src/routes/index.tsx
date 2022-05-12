import React from 'react';
import { Route, Switch } from 'react-router-dom';

import { WButton } from 'wdx';

import { EPeopleRouterLink } from 'models/people-router-link';
import AsyncPeopleApp from 'remotes/AsyncPeopleApp';

export default function Routes(): JSX.Element {
    return (
        <Switch>
            <Route path={EPeopleRouterLink.People}>
                <AsyncPeopleApp />
            </Route>
            <Route exact path="/">
                <Home />
            </Route>
        </Switch>
    );
}

const Home = (): JSX.Element => (
    <div className="HostAppHome">
        <h1>Host App</h1>
        <WButton>click me</WButton>
    </div>
);
