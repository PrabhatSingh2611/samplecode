import React from 'react';
import { HelmetProvider } from 'react-helmet-async';

import PeopleObservables from 'core/PeopleObservables';
import Router from 'core/Router';
import { FlagsProvider } from 'flagged';
import Routes from 'routes';
import { WBox } from 'wdx';

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
                        <Routes />
                    </Router>
                </WBox>
            </HelmetProvider>
        </FlagsProvider>
    );
}

export default App;
