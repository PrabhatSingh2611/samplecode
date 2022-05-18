import React from 'react';
import { Link, Route, Switch } from 'react-router-dom';

import { FlagsProvider } from 'flagged';
import { WBox, WTypography } from 'wdx';

import { ApolloProvider } from '@apollo/client';

import { getClient } from 'client';
import RemoteExampleObservables from 'core/RemoteExampleObservavbles';
import Router from 'core/Router';
import EmployeesList from 'features/employees/components/EmployeesList.component';
import AuthorizationBtn from 'features/me/components/authBtn';

interface IAppProps {
    inIsolation: boolean;
    initialEntry?: string;
}

function App({ inIsolation, initialEntry }: IAppProps): JSX.Element {
    return (
        <ApolloProvider client={getClient(inIsolation)}>
            <FlagsProvider>
                <WBox className="RemoteExampleApp">
                    <WTypography>Hello from Remote Example App!</WTypography>
                    <Router inIsolation={inIsolation} initialEntry={initialEntry}>
                        <RemoteExampleObservables />
                        <Header />
                        <Switch>
                            <Route path="/details">
                                <Details />
                            </Route>
                            <Route path="/">
                                <Home />
                            </Route>
                        </Switch>
                    </Router>
                </WBox>
            </FlagsProvider>
        </ApolloProvider>
    );
}

const Header = (): JSX.Element => (
    <header className="RemoteExampleAppHeader">
        <nav>
            <Link to="/">Home</Link> | <Link to="/details">Details</Link>
        </nav>
    </header>
);

const Home = (): JSX.Element => (
    <WBox className="RemoteExampleAppHome">
        <WTypography>Remote Example App</WTypography>
        <AuthorizationBtn />
    </WBox>
);

const Details = (): JSX.Element => (
    <WBox className="RemoteExampleAppDetatils">
        <WTypography>Remote Example App Details</WTypography>
        <EmployeesList />
    </WBox>
);

export default App;
