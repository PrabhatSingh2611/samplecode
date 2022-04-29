import React from 'react';
import { BrowserRouter, MemoryRouter } from 'react-router-dom';

export interface RouterParams {
    children: React.ReactNode;
    inIsolation: boolean;
}

export default function Router({ children, inIsolation }: RouterParams): JSX.Element {
    return inIsolation ? (
        <BrowserRouter>{children}</BrowserRouter>
    ) : (
        <MemoryRouter>{children}</MemoryRouter>
    );
}
