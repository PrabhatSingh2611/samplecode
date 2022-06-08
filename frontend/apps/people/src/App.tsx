import { HelmetProvider } from 'react-helmet-async';

import PeopleObservables from 'core/PeopleObservables';
import Router from 'core/Router';
import { FlagsProvider } from 'flagged';
import Routes from 'routes';
import { WAdapterDateFns, WLocalizationProvider, ThemeProvider } from 'wdx';

import { ApolloProvider } from '@apollo/client';

import { getClient } from 'graphql/client';

interface IAppProps {
    inIsolation: boolean;
    initialEntry?: string;
}

function App({ inIsolation, initialEntry }: IAppProps): JSX.Element {
    return (
        <ApolloProvider client={getClient(inIsolation)}>
            <FlagsProvider>
                <HelmetProvider>
                    <WLocalizationProvider dateAdapter={WAdapterDateFns}>
                        {/* TODO: Remove ThemeProvider when it will be fixed (AU) */}
                        <ThemeProvider>
                            <Router inIsolation={inIsolation} initialEntry={initialEntry}>
                                <PeopleObservables />
                                <Routes />
                            </Router>
                        </ThemeProvider>
                    </WLocalizationProvider>
                </HelmetProvider>
            </FlagsProvider>
        </ApolloProvider>
    );
}

export default App;
