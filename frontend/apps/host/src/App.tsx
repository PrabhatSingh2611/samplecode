import React from 'react';
import { HelmetProvider } from 'react-helmet-async';
import { BrowserRouter } from 'react-router-dom';

import { unstable_ClassNameGenerator as ClassNameGenerator } from '@mui/material/className';
import { FlagsProvider } from 'flagged';

import MotionLazyContainer from 'components/MotionLazyContainer';
import ThemeSettingsProvider from 'components/ThemeSettingsProvider';
import ThemeSettings from 'components/settings';
import { CollapseDrawerProvider } from 'contexts/CollapseDrawerContext';
import { SettingsProvider } from 'contexts/SettingsContext';
import HostObservables from 'core/HostObservables';
import DashboardLayout from 'layouts/dashboard';

// NOTE - Set these lines in your remote and change the host value to the name of your remote (TH)
// IMPORTANT - Call this function at the root of the application
ClassNameGenerator.configure((componentName) => `host-${componentName}`);

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
                                        <DashboardLayout />
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
