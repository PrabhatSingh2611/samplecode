import React from 'react';

import ThemeColorPresets from 'components/settings/ThemeColorPresets';
import ThemeContrast from 'components/settings/ThemeContrast';
import ThemeRtlLayout from 'components/settings/ThemeRtlLayout';
import SettingsDrawer from 'components/settings/drawer';

interface IProps {
    children: React.ReactNode;
}

export default function ThemeSettings({ children }: IProps): JSX.Element {
    return (
        <ThemeColorPresets>
            <ThemeContrast>
                <ThemeRtlLayout>
                    {children}
                    <SettingsDrawer />
                </ThemeRtlLayout>
            </ThemeContrast>
        </ThemeColorPresets>
    );
}
