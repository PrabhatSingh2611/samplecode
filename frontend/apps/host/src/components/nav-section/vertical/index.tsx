import React from 'react';

import { List, Box, ListSubheader, SxProps } from '@mui/material';
import { styled } from '@mui/material/styles';
import { INavSectionProps } from 'components/nav-section/type';
import { NavListRoot } from 'components/nav-section/vertical/NavList';

interface IProps {
    sx?: SxProps;
    children: string;
}

export const ListSubheaderStyle = styled((props: IProps) => (
    <ListSubheader disableSticky disableGutters {...props} />
))(({ theme }) => ({
    ...theme.typography.overline,
    paddingTop: theme.spacing(3),
    paddingLeft: theme.spacing(2),
    paddingBottom: theme.spacing(1),
    color: theme.palette.text.primary,
    transition: theme.transitions.create('opacity', {
        duration: theme.transitions.duration.shorter,
    }),
}));

export default function NavSectionVertical({
    navConfig,
    isCollapse = false,
    ...other
}: INavSectionProps): JSX.Element {
    return (
        <Box {...other}>
            {navConfig.map((group) => (
                <List key={group.subheader} disablePadding sx={{ px: 2 }}>
                    {group.items.map((list) => (
                        <NavListRoot
                            key={list.title + list.path}
                            list={list}
                            isCollapse={isCollapse}
                        />
                    ))}
                </List>
            ))}
        </Box>
    );
}
