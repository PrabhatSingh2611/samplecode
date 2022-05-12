import React, { useState, useEffect, useRef } from 'react';
import { useLocation } from 'react-router-dom';

import { NavItemRoot, NavItemSub } from 'components/nav-section/horizontal/NavItem';
import { PaperStyle } from 'components/nav-section/horizontal/NavListItem';
import { NavListProps } from 'components/nav-section/type';
import { getActive } from 'components/nav-section/utils/nav-section.utils';

interface INavListRootProps {
    list: NavListProps;
}

export function NavListRoot({ list }: INavListRootProps): JSX.Element {
    const menuRef = useRef(null);

    const { pathname } = useLocation();

    const active = getActive(list.path, pathname);

    const [open, setOpen] = useState(false);

    const hasChildren = list.children;

    useEffect(() => {
        if (open) {
            handleClose();
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [pathname]);

    const handleOpen = (): void => {
        setOpen(true);
    };

    const handleClose = (): void => {
        setOpen(false);
    };

    if (hasChildren) {
        return (
            <>
                <NavItemRoot
                    open={open}
                    item={list}
                    active={active}
                    ref={menuRef}
                    onMouseEnter={handleOpen}
                    onMouseLeave={handleClose}
                />

                <PaperStyle
                    open={open}
                    anchorEl={menuRef.current}
                    anchorOrigin={{ vertical: 'bottom', horizontal: 'left' }}
                    transformOrigin={{ vertical: 'top', horizontal: 'left' }}
                    PaperProps={{
                        onMouseEnter: handleOpen,
                        onMouseLeave: handleClose,
                    }}
                >
                    {(list.children || []).map((item) => (
                        <NavListSub key={item.title + item.path} list={item} />
                    ))}
                </PaperStyle>
            </>
        );
    }

    return <NavItemRoot item={list} active={active} />;
}

interface INavListSubProps {
    list: NavListProps;
}

function NavListSub({ list }: INavListSubProps): JSX.Element {
    const menuRef = useRef(null);

    const { pathname } = useLocation();

    const active = getActive(list.path, pathname);

    const [open, setOpen] = useState(false);

    const handleOpen = (): void => {
        setOpen(true);
    };

    const handleClose = (): void => {
        setOpen(false);
    };

    const hasChildren = list.children;

    if (hasChildren) {
        return (
            <>
                <NavItemSub
                    ref={menuRef}
                    open={open}
                    item={list}
                    active={active}
                    onMouseEnter={handleOpen}
                    onMouseLeave={handleClose}
                />

                <PaperStyle
                    open={open}
                    anchorEl={menuRef.current}
                    anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
                    transformOrigin={{ vertical: 'top', horizontal: 'left' }}
                    PaperProps={{
                        onMouseEnter: handleOpen,
                        onMouseLeave: handleClose,
                    }}
                >
                    {(list.children || []).map((item) => (
                        <NavListSub key={item.title + item.path} list={item} />
                    ))}
                </PaperStyle>
            </>
        );
    }

    return <NavItemSub item={list} active={active} />;
}
