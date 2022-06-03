import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import { WBox, WTypography } from 'wdx';

import { EAssetsRouterLink } from 'models/assets-router-link';
import { EPeopleRouterLink } from 'models/people-router-link';
import { ERecruitmentRouterLink } from 'models/recruitment-router-llink';
import AsyncAssetsApp from 'remotes/AsyncAssetsApp';
import AsyncPeopleApp from 'remotes/AsyncPeopleApp';

export default function Routes(): JSX.Element {
    return (
        <Switch>
            <Route path={EPeopleRouterLink.People}>
                <AsyncPeopleApp />
            </Route>
            <Route path={EAssetsRouterLink.Assets}>
                <AsyncAssetsApp />
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

function Page({ title }: { title: string }): JSX.Element {
    return (
        <WBox className="HostAppHome">
            <WTypography>{title}</WTypography>
        </WBox>
    );
}
