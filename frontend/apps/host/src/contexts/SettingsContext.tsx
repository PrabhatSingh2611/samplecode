import React, { ReactNode, useEffect, createContext } from 'react';

import {
    ThemeMode,
    ThemeLayout,
    ThemeContrast,
    ThemeDirection,
    ThemeColorPresets,
    SettingsContextProps,
} from 'components/settings/type';
import useLocalStorage from 'hooks/useLocalStorage';
import { defaultSettings } from 'theme/config';
import getColorPresets, { colorPresets, defaultPreset } from 'utils/getColorPresets';

const initialState: SettingsContextProps = {
    ...defaultSettings,
    // TODO:fix types for onToggleMode ext. (VZ)
    // Mode
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onToggleMode: () => {},
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onChangeMode: () => {},

    // Direction
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onToggleDirection: () => {},
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onChangeDirection: () => {},
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onChangeDirectionByLang: () => {},

    // Layout
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onToggleLayout: () => {},
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onChangeLayout: () => {},

    // Contrast
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onToggleContrast: () => {},
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onChangeContrast: () => {},

    // Color
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onChangeColor: () => {},
    setColor: defaultPreset,
    colorOption: [],

    // Stretch
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onToggleStretch: () => {},

    // Reset
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onResetSetting: () => {},
};

const SettingsContext = createContext(initialState);

type SettingsProviderProps = {
    children: ReactNode;
};

function SettingsProvider({ children }: SettingsProviderProps): JSX.Element {
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

export { SettingsProvider, SettingsContext };
