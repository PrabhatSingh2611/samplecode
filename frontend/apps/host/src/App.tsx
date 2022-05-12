import React from 'react';
import { BrowserRouter } from 'react-router-dom';

import HostObservables from 'core/HostObservables';

import { HelmetProvider } from 'react-helmet-async';
import MotionLazyContainer from 'components/MotionLazyContainer';
import ThemeSettings from 'components/settings';
import { SettingsProvider } from 'contexts/SettingsContext';
import { CollapseDrawerProvider } from 'contexts/CollapseDrawerContext';
import DashboardLayout from 'layouts/dashboard';
import ThemeSettingsProvider from 'components/ThemeSettingsProvider';

// NOTE - Set these lines in your remote and change the host value to the name of your remote (TH)
// IMPORTANT - Call this function at the root of the application
import { unstable_ClassNameGenerator as ClassNameGenerator } from '@mui/material/className';
ClassNameGenerator.configure((componentName) => `host-${componentName}`);

export default function App() {
    return (
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
    );
}
