import React from 'react';
import { BrowserRouter, MemoryRouter } from 'react-router-dom';

interface IRouterParams {
    children: React.ReactNode;
    inIsolation: boolean;
    initialEntry?: string;
}

export default function Router({
    children,
    inIsolation,
    initialEntry,
}: IRouterParams): JSX.Element {
    const initialEntries = initialEntry ? [initialEntry] : ['/'];

    return inIsolation ? (
        <BrowserRouter>{children}</BrowserRouter>
    ) : (
        <MemoryRouter initialEntries={initialEntries}>{children}</MemoryRouter>
    );
}
