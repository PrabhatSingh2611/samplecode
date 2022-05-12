import React, { memo } from 'react';

import { Stack } from '@mui/material';

import { NavListRoot } from 'components/nav-section/horizontal/NavList';
import { INavSectionProps } from 'components/nav-section/type';

const hideScrollbar = {
    msOverflowStyle: 'none',
    scrollbarWidth: 'none',
    overflowY: 'scroll',
    '&::-webkit-scrollbar': {
        display: 'none',
    },
} as const;

function NavSectionHorizontal({ navConfig }: INavSectionProps): JSX.Element {
    return (
        <Stack
            direction="row"
            justifyContent="center"
            sx={{ px: 0.5, borderRadius: 1, bgcolor: 'background.neutral' }}
        >
            <Stack direction="row" sx={{ ...hideScrollbar, py: 1 }}>
                {navConfig.map((group) => (
                    <Stack key={group.subheader} direction="row" flexShrink={0}>
                        {group.items.map((list) => (
                            <NavListRoot key={list.title + list.path} list={list} />
                        ))}
                    </Stack>
                ))}
            </Stack>
        </Stack>
    );
}

export default memo(NavSectionHorizontal);
