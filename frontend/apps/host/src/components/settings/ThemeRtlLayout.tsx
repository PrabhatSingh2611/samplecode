import React, { useEffect, ReactNode } from 'react';

import createCache from '@emotion/cache';
import { CacheProvider } from '@emotion/react';
import { useTheme } from '@mui/material/styles';
import rtlPlugin from 'stylis-plugin-rtl';

type Props = {
    children: ReactNode;
};

export default function ThemeRtlLayout({ children }: Props): JSX.Element {
    const theme = useTheme();

    useEffect(() => {
        document.dir = theme.direction;
    }, [theme.direction]);

    const cacheRtl = createCache({
        key: theme.direction === 'rtl' ? 'rtl' : 'css',
        stylisPlugins: theme.direction === 'rtl' ? [rtlPlugin] : [],
    });

    return <CacheProvider value={cacheRtl}>{children}</CacheProvider>;
}
