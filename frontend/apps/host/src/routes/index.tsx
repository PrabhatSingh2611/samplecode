import React from 'react';
import { Redirect, Route, Switch, useRouteMatch } from 'react-router-dom';

import { WBox, WTypography } from 'wdx';

import { EAssetsRouterLink } from 'models/assets-router-link';
import { EPeopleRouterLink } from 'models/people-router-link';
import { ERecruitmentRouterLink } from 'models/recruitment-router-llink';
import { ESettingsRouterLink } from 'models/settings-router-link';
import AsyncPeopleApp from 'remotes/AsyncPeopleApp';

export default function Routes(): JSX.Element {
    return (
        <Switch>
            <Route path={EPeopleRouterLink.People}>
                <AsyncPeopleApp />
            </Route>
            <Route path={EAssetsRouterLink.Assets}>
                <Page title="Assets page" />
            </Route>
            <Route path={ERecruitmentRouterLink.Recruitment}>
                <Page title="Recruitment page" />
            </Route>
            <Route path={ESettingsRouterLink.Settings}>
                <SettingsRoutes />
            </Route>
            <Route exact path="/">
                <Redirect to={EPeopleRouterLink.People} />
            </Route>
        </Switch>
    );
}

function SettingsRoutes(): JSX.Element {
    const { path } = useRouteMatch();

    const peoplePath = path + ESettingsRouterLink.People;

    return (
        <Switch>
            <Route path={peoplePath + ESettingsRouterLink.Positions}>
                <Page title="Settings positions" />
            </Route>
            <Route path={peoplePath + ESettingsRouterLink.Locations}>
                <Page title="Settings locations" />
            </Route>
            <Route path={path + ESettingsRouterLink.Assets + ESettingsRouterLink.Types}>
                <Page title="Settings assets types" />
            </Route>
            <Route path={peoplePath + ESettingsRouterLink.Me}>
                <Page title="Settings me" />
            </Route>
            <Route exact={true} path={peoplePath}>
                <Redirect to={peoplePath + ESettingsRouterLink.Positions} />
            </Route>
        </Switch>
    );
}

function Page({ title }: { title: string }): JSX.Element {
    return (
        <WBox className="HostAppHome">
            <WTypography>{title}</WTypography>
        </WBox>
    );
}
