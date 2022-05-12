import { ReactNode } from 'react';
import { ThemeProvider } from 'wdx';
import useSettings from 'hooks/useSettings';

declare type Props = {
    children: ReactNode;
};

export default function ThemeSettingsProvider({ children }: Props): JSX.Element {
    const { themeMode, themeDirection } = useSettings();

    return (
        <ThemeProvider themeMode={themeMode} themeDirection={themeDirection}>
            {children}
        </ThemeProvider>
    );
}
