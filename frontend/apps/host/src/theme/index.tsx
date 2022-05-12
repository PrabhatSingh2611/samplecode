import { useMemo, ReactNode } from 'react';

import { CssBaseline } from '@mui/material';
import {
    createTheme,
    ThemeOptions,
    ThemeProvider as MUIThemeProvider,
    StyledEngineProvider,
} from '@mui/material/styles';

import useSettings from 'hooks/useSettings';
import breakpoints from 'theme/breakpoints';
import componentsOverride from 'theme/overrides';
import palette from 'theme/palette';
import shadows, { customShadows } from 'theme/shadows';
import typography from 'theme/typography';

type Props = {
    children: ReactNode;
};

export default function ThemeProvider({ children }: Props): JSX.Element {
    const { themeMode, themeDirection } = useSettings();

    const isLight = themeMode === 'light';

    const themeOptions: ThemeOptions = useMemo(
        () => ({
            palette: isLight ? palette.light : palette.dark,
            typography,
            breakpoints,
            shape: { borderRadius: 8 },
            direction: themeDirection,
            shadows: isLight ? shadows.light : shadows.dark,
            customShadows: isLight ? customShadows.light : customShadows.dark,
        }),
        [isLight, themeDirection],
    );

    const theme = createTheme(themeOptions);

    theme.components = componentsOverride(theme);

    return (
        <StyledEngineProvider injectFirst>
            <MUIThemeProvider theme={theme}>
                <CssBaseline />
                {children}
            </MUIThemeProvider>
        </StyledEngineProvider>
    );
}
