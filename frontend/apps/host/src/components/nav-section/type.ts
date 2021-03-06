import { ReactElement } from 'react';

import { BoxProps } from '@mui/material';

export type NavListProps = {
    title: string;
    path: string;
    icon?: ReactElement;
    info?: ReactElement;
    caption?: string;
    disabled?: boolean;
    roles?: string[];
    children?: {
        title: string;
        path: string;
        children?: { title: string; path: string }[];
    }[];
};

export type NavItemProps = {
    item: NavListProps;
    isCollapse?: boolean;
    active?: boolean;
    open?: boolean;
    onOpen?: VoidFunction;
    onMouseEnter?: VoidFunction;
    onMouseLeave?: VoidFunction;
};

export interface INavSectionProps extends BoxProps {
    isCollapse?: boolean;
    navConfig: {
        subheader: string;
        items: NavListProps[];
    }[];
}
