import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';

import { List, Collapse } from '@mui/material';

import { NavListProps } from 'components/nav-section/type';
import { getActive } from 'components/nav-section/utils/nav-section.utils';
import { NavItemRoot, NavItemSub } from 'components/nav-section/vertical/NavItem';

interface INavListRootProps {
    list: NavListProps;
    isCollapse: boolean;
}

export function NavListRoot({ list, isCollapse }: INavListRootProps): JSX.Element {
    const { pathname } = useLocation();

    const active = getActive(list.path, pathname);

    const [open, setOpen] = useState(active);

    const hasChildren = list.children;

    if (hasChildren) {
        return (
            <>
                <NavItemRoot
                    item={list}
                    isCollapse={isCollapse}
                    active={active}
                    open={open}
                    onOpen={(): void => setOpen(!open)}
                />

                {!isCollapse && (
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <List component="div" disablePadding>
                            {(list.children || []).map((item) => (
                                <NavListSub key={item.title + item.path} list={item} />
                            ))}
                        </List>
                    </Collapse>
                )}
            </>
        );
    }

    return <NavItemRoot item={list} active={active} isCollapse={isCollapse} />;
}

interface INavListSubProps {
    list: NavListProps;
}

function NavListSub({ list }: INavListSubProps): JSX.Element {
    const { pathname } = useLocation();

    const active = getActive(list.path, pathname);

    const [open, setOpen] = useState(active);

    const hasChildren = list.children;

    if (hasChildren) {
        return (
            <>
                <NavItemSub
                    item={list}
                    onOpen={(): void => setOpen(!open)}
                    open={open}
                    active={active}
                />

                <Collapse in={open} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding sx={{ pl: 3 }}>
                        {(list.children || []).map((item) => (
                            <NavItemSub
                                key={item.title + item.path}
                                item={item}
                                active={getActive(item.path, pathname)}
                            />
                        ))}
                    </List>
                </Collapse>
            </>
        );
    }

    return <NavItemSub item={list} active={active} />;
}
