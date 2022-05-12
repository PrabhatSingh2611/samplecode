import React from 'react';
import { HelmetProvider } from 'react-helmet-async';
import { Redirect, Route, Switch } from 'react-router-dom';

import { WBox } from 'wdx';

import { unstable_ClassNameGenerator as ClassNameGenerator } from '@mui/material/className';
import { FlagsProvider } from 'flagged';

import { EEmployeesRouterLink } from 'features/employees/models/employees-router-link';
import { ELeavesRouterLink } from 'features/leaves/models/leaves-router-link';
import { EMeRouterLink } from 'features/me/models/me-router-link';
import PolicyPage from 'features/policy/containers/policy-list-page/policy-list-page.container';
import { EPolicyRouterLink } from 'features/policy/models/policy-router-link.enum';

import PeopleObservables from 'core/PeopleObservables';
import Router from 'core/Router';

// NOTE - Set these lines in your remote and change the host value to the name of your remote (TH)
// IMPORTANT - Call this function at the root of the application
// import { unstable_ClassNameGenerator as ClassNameGenerator } from '@mui/material/className';
// ClassNameGenerator.configure((componentName) => `people-${componentName}`);

ClassNameGenerator.configure((componentName) => `people-${componentName}`);

interface IAppProps {
    inIsolation: boolean;
    initialEntry?: string;
}

function App({ inIsolation, initialEntry }: IAppProps): JSX.Element {
    return (
        <FlagsProvider>
            <HelmetProvider>
                <WBox className="PeopleApp">
                    <Router inIsolation={inIsolation} initialEntry={initialEntry}>
                        <PeopleObservables />
                        <Switch>
                            <Route path={EMeRouterLink.Me}>
                                <Details title="Me page" />
                            </Route>
                            <Route path={EPolicyRouterLink.Policy}>
                                <PolicyPage />
                            </Route>
                            <Route path={EEmployeesRouterLink.Employees}>
                                <Details title="Employees page" />
                            </Route>
                            <Route path={ELeavesRouterLink.Leaves}>
                                <Details title="Leaves page" />
                            </Route>
                            <Route exact={true} path="/">
                                <Redirect to={EMeRouterLink.Me} />
                            </Route>
                        </Switch>
                    </Router>
                </WBox>
            </HelmetProvider>
        </FlagsProvider>
    );
}

function Details({ title }: { title: string }): JSX.Element {
    return (
        <div className="PeopleAppDetails">
            <h1>{title}</h1>
        </div>
    );
}

export default App;
