import React from 'react';
import { HelmetProvider } from 'react-helmet-async';
import { BrowserRouter } from 'react-router-dom';

import { FlagsProvider } from 'flagged';

import { ApolloProvider } from '@apollo/client';

import { client } from 'client';
import MotionLazyContainer from 'components/MotionLazyContainer';
import ThemeSettingsProvider from 'components/ThemeSettingsProvider';
import ThemeSettings from 'components/settings';
import { CollapseDrawerProvider } from 'contexts/CollapseDrawerContext';
import { SettingsProvider } from 'contexts/SettingsContext';
import HostObservables from 'core/HostObservables';
import Layout from 'layout';

export default function App(): JSX.Element {
    return (
        <ApolloProvider client={client}>
            <FlagsProvider>
                <HelmetProvider>
                    <SettingsProvider>
                        <CollapseDrawerProvider>
                            <BrowserRouter>
                                <HostObservables />
                                <MotionLazyContainer>
                                    <ThemeSettingsProvider>
                                        <ThemeSettings>
                                            <Layout />
                                        </ThemeSettings>
                                    </ThemeSettingsProvider>
                                </MotionLazyContainer>
                            </BrowserRouter>
                        </CollapseDrawerProvider>
                    </SettingsProvider>
                </HelmetProvider>
            </FlagsProvider>
        </ApolloProvider>
    );
}
