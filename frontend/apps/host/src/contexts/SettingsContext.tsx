import React, { ReactNode, useEffect, createContext } from 'react';

import {
    ThemeMode,
    ThemeLayout,
    ThemeContrast,
    ThemeDirection,
    ThemeColorPresets,
    SettingsContextProps,
    SettingsValueProps,
} from 'components/settings/type';
import useLocalStorage from 'hooks/useLocalStorage';
import getColorPresets, { colorPresets, defaultPreset } from 'utils/getColorPresets';

const defaultSettings: SettingsValueProps = {
    themeMode: 'light',
    themeDirection: 'ltr',
    themeContrast: 'default',
    themeLayout: 'horizontal',
    themeColorPresets: 'blue',
    themeStretch: false,
};

const initialState: SettingsContextProps = {
    ...defaultSettings,
    // Mode
    onToggleMode: () => {
        // Empty function.
    },
    onChangeMode: () => {
        // Empty function.
    },

    // Direction
    onToggleDirection: () => {
        // Empty function.
    },
    onChangeDirection: () => {
        // Empty function.
    },
    onChangeDirectionByLang: () => {
        // Empty function.
    },

    // Layout
    onToggleLayout: () => {
        // Empty function.
    },
    onChangeLayout: () => {
        // Empty function.
    },

    // Contrast
    onToggleContrast: () => {
        // Empty function.
    },
    onChangeContrast: () => {
        // Empty function.
    },

    // Color
    onChangeColor: () => {
        // Empty function.
    },
    setColor: defaultPreset,
    colorOption: [],

    // Stretch
    onToggleStretch: () => {
        // Empty function.
    },

    // Reset
    onResetSetting: () => {
        // Empty function.
    },
};

export const SettingsContext = createContext(initialState);

type SettingsProviderProps = {
    children: ReactNode;
};

export default function SettingsProvider({ children }: SettingsProviderProps): JSX.Element {
    const [settings, setSettings] = useLocalStorage('settings', {
        themeMode: initialState.themeMode,
        themeLayout: initialState.themeLayout,
        themeStretch: initialState.themeStretch,
        themeContrast: initialState.themeContrast,
        themeDirection: initialState.themeDirection,
        themeColorPresets: initialState.themeColorPresets,
    });

    const isArabic = localStorage.getItem('i18nextLng') === 'ar';

    useEffect(() => {
        if (isArabic) {
            onChangeDirectionByLang('ar');
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [isArabic]);

    // Mode
    const onToggleMode = (): void => {
        setSettings({
            ...settings,
            themeMode: settings.themeMode === 'light' ? 'dark' : 'light',
        });
    };

    const onChangeMode = (event: React.ChangeEvent<HTMLInputElement>): void => {
        setSettings({
            ...settings,
            themeMode: (event.target as HTMLInputElement).value as ThemeMode,
        });
    };

    // Direction
    const onToggleDirection = (): void => {
        setSettings({
            ...settings,
            themeDirection: settings.themeDirection === 'rtl' ? 'ltr' : 'rtl',
        });
    };

    const onChangeDirection = (event: React.ChangeEvent<HTMLInputElement>): void => {
        setSettings({
            ...settings,
            themeDirection: (event.target as HTMLInputElement).value as ThemeDirection,
        });
    };

    const onChangeDirectionByLang = (lang: string): void => {
        setSettings({
            ...settings,
            themeDirection: lang === 'ar' ? 'rtl' : 'ltr',
        });
    };

    // Layout
    const onToggleLayout = (): void => {
        setSettings({
            ...settings,
            themeLayout: settings.themeLayout === 'vertical' ? 'horizontal' : 'vertical',
        });
    };

    const onChangeLayout = (event: React.ChangeEvent<HTMLInputElement>): void => {
        setSettings({
            ...settings,
            themeLayout: (event.target as HTMLInputElement).value as ThemeLayout,
        });
    };

    // Contrast
    const onToggleContrast = (): void => {
        setSettings({
            ...settings,
            themeContrast: settings.themeContrast === 'default' ? 'bold' : 'default',
        });
    };

    const onChangeContrast = (event: React.ChangeEvent<HTMLInputElement>): void => {
        setSettings({
            ...settings,
            themeContrast: (event.target as HTMLInputElement).value as ThemeContrast,
        });
    };

    // Color
    const onChangeColor = (event: React.ChangeEvent<HTMLInputElement>): void => {
        setSettings({
            ...settings,
            themeColorPresets: (event.target as HTMLInputElement).value as ThemeColorPresets,
        });
    };

    // Stretch
    const onToggleStretch = (): void => {
        setSettings({
            ...settings,
            themeStretch: !settings.themeStretch,
        });
    };

    // Reset
    const onResetSetting = (): void => {
        setSettings({
            themeMode: initialState.themeMode,
            themeLayout: initialState.themeLayout,
            themeStretch: initialState.themeStretch,
            themeContrast: initialState.themeContrast,
            themeDirection: initialState.themeDirection,
            themeColorPresets: initialState.themeColorPresets,
        });
    };

    return (
        <SettingsContext.Provider
            value={{
                ...settings,

                // Mode
                onToggleMode,
                onChangeMode,

                // Direction
                onToggleDirection,
                onChangeDirection,
                onChangeDirectionByLang,

                // Layout
                onToggleLayout,
                onChangeLayout,

                // Contrast
                onChangeContrast,
                onToggleContrast,

                // Stretch
                onToggleStretch,

                // Color
                onChangeColor,
                setColor: getColorPresets(settings.themeColorPresets),
                colorOption: colorPresets.map((color) => ({
                    name: color.name,
                    value: color.main,
                })),

                // Reset
                onResetSetting,
            }}
        >
            {children}
        </SettingsContext.Provider>
    );
}
