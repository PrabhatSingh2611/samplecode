import React from 'react';
import { Redirect, Route, Switch, useRouteMatch } from 'react-router-dom';

import { WBox, WTypography } from 'wdx';

import { EAssetsRouterLink } from 'models/assets-router-link';
import { EPeopleRouterLink } from 'models/people-router-link';
import { ERecruitmentRouterLink } from 'models/recruitment-router-llink';
import AsyncPeopleApp from 'remotes/AsyncPeopleApp';

export default function Routes(): JSX.Element {
    return (
        <Switch>
            <Route path={EPeopleRouterLink.People}>
                <AsyncPeopleApp />
            </Route>
            <Route path={EAssetsRouterLink.Assets}>
                <AssetsRoutes />
            </Route>
            <Route path={ERecruitmentRouterLink.Recruitment}>
                <Page title="Recruitment page" />
            </Route>
            <Route exact path="/">
                <Redirect to={EPeopleRouterLink.People} />
            </Route>
        </Switch>
    );
}

function AssetsRoutes(): JSX.Element {
    const { path } = useRouteMatch();

    return (
        <Switch>
            <Route path={path + EAssetsRouterLink.Types}>
                <Page title="Assets types page" />
            </Route>

            <Route path={path}>
                <Page title="Assets page" />
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
