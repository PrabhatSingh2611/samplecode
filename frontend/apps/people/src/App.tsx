import { HelmetProvider } from 'react-helmet-async';

import { FlagsProvider } from 'flagged';
import { WAdapterDateFns, WLocalizationProvider, ThemeProvider, WBox } from 'wdx';

import { ApolloProvider } from '@apollo/client';

import { getClient } from 'graphql/client';

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
                    <WLocalizationProvider dateAdapter={WAdapterDateFns}>
                        {/* TODO: Remove ThemeProvider when it will be fixed (AU) */}
                        <ThemeProvider>
                            <WBox className="PeopleApp">
                                <Router inIsolation={inIsolation} initialEntry={initialEntry}>
                                    <PeopleObservables />
                                    <Routes />
                                </Router>
                            </WBox>
                        </ThemeProvider>
                    </WLocalizationProvider>
                </HelmetProvider>
            </FlagsProvider>
        </ApolloProvider>
    );
}

export default App;
