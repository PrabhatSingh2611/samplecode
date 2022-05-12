import React, { ReactNode, createContext, useState, useEffect } from 'react';

import useResponsive from 'hooks/useResponsive';

export type CollapseDrawerContextProps = {
    isCollapse?: boolean;
    collapseClick: boolean;
    collapseHover: boolean;
    onToggleCollapse: VoidFunction;
    onHoverEnter: VoidFunction;
    onHoverLeave: VoidFunction;
};

const initialState: CollapseDrawerContextProps = {
    collapseClick: false,
    collapseHover: false,
    // TODO:fix types for onToggleCollapse ext. (VZ)
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onToggleCollapse: () => {},
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onHoverEnter: () => {},
    // eslint-disable-next-line @typescript-eslint/no-empty-function
    onHoverLeave: () => {},
};

const CollapseDrawerContext = createContext(initialState);

interface ICollapseDrawerProviderProps {
    children: ReactNode;
}

function CollapseDrawerProvider({ children }: ICollapseDrawerProviderProps): JSX.Element {
    const isDesktop = useResponsive('up', 'lg');

    const [collapse, setCollapse] = useState({
        click: false,
        hover: false,
    });

    useEffect(() => {
        if (!isDesktop) {
            setCollapse({
                click: false,
                hover: false,
            });
        }
    }, [isDesktop]);

    const handleToggleCollapse = (): void => {
        setCollapse({ ...collapse, click: !collapse.click });
    };

    const handleHoverEnter = (): void => {
        if (collapse.click) {
            setCollapse({ ...collapse, hover: true });
        }
    };

    const handleHoverLeave = (): void => {
        setCollapse({ ...collapse, hover: false });
    };

    return (
        <CollapseDrawerContext.Provider
            value={{
                isCollapse: collapse.click && !collapse.hover,
                collapseClick: collapse.click,
                collapseHover: collapse.hover,
                onToggleCollapse: handleToggleCollapse,
                onHoverEnter: handleHoverEnter,
                onHoverLeave: handleHoverLeave,
            }}
        >
            {children}
        </CollapseDrawerContext.Provider>
    );
}

export { CollapseDrawerProvider, CollapseDrawerContext };
