import { HelmetProvider } from 'react-helmet-async';

import { FlagsProvider } from 'flagged';
import { ThemeProvider } from 'wdx';

import { ApolloProvider } from '@apollo/client';

import { getClient } from 'graphql/client';

import AssetsObservables from 'core/AssetsObservavbles';
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
                    <ThemeProvider>
                        <Router inIsolation={inIsolation} initialEntry={initialEntry}>
                            <AssetsObservables />
                            <Routes />
                        </Router>
                    </ThemeProvider>
                </HelmetProvider>
            </FlagsProvider>
        </ApolloProvider>
    );
}

export default App;

