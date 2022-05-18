import React from 'react';
import { HelmetProvider } from 'react-helmet-async';

import { FlagsProvider } from 'flagged';
import { WBox } from 'wdx';

import { ApolloProvider } from '@apollo/client';

import { getClient } from 'client';
import PeopleObservables from 'core/PeopleObservables';
import Router from 'core/Router';
import Routes from 'routes';

interface IAppProps {
    inIsolation: boolean;
    initialEntry?: string;
}

function App({ inIsolation, initialEntry }: IAppProps): JSX.Element {
    return (
        <ApolloProvider client={getClient(inIsolation)}>
            <FlagsProvider>
                <HelmetProvider>
                    <WBox className="PeopleApp">
                        <Router inIsolation={inIsolation} initialEntry={initialEntry}>
                            <PeopleObservables />
                            <Routes />
                        </Router>
                    </WBox>
                </HelmetProvider>
            </FlagsProvider>
        </ApolloProvider>
    );
}

export default App;
