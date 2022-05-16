import React from 'react';
import { HelmetProvider } from 'react-helmet-async';

import { unstable_ClassNameGenerator as ClassNameGenerator } from '@mui/material/className';
import { FlagsProvider } from 'flagged';
import { WBox } from 'wdx';

import PeopleObservables from 'core/PeopleObservables';
import Router from 'core/Router';
import Routes from 'routes';

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
                        <Routes />
                    </Router>
                </WBox>
            </HelmetProvider>
        </FlagsProvider>
    );
}

export default App;
