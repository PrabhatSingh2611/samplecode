import React from 'react';
import { HelmetProvider } from 'react-helmet-async';
import { BrowserRouter } from 'react-router-dom';

import { FlagsProvider } from 'flagged';

import MotionLazyContainer from 'components/MotionLazyContainer';
import ThemeSettingsProvider from 'components/ThemeSettingsProvider';
import ThemeSettings from 'components/settings';
import { CollapseDrawerProvider } from 'contexts/CollapseDrawerContext';
import { SettingsProvider } from 'contexts/SettingsContext';
import HostObservables from 'core/HostObservables';
import AppLayout from 'layouts/app';

export default function App(): JSX.Element {
    return (
        <FlagsProvider>
            <HelmetProvider>
                <SettingsProvider>
                    <CollapseDrawerProvider>
                        <BrowserRouter>
                            <HostObservables />
                            <MotionLazyContainer>
                                <ThemeSettingsProvider>
                                    <ThemeSettings>
                                        <AppLayout />
                                    </ThemeSettings>
                                </ThemeSettingsProvider>
                            </MotionLazyContainer>
                        </BrowserRouter>
                    </CollapseDrawerProvider>
                </SettingsProvider>
            </HelmetProvider>
        </FlagsProvider>
    );
}
