import React from 'react';

import { createRoot } from 'react-dom/client';

import App from 'App';

import { hostNavigateObservable } from './core/observable.hooks';
import reportWebVitals from './reportWebVitals';

import './index.css';

interface IMountProps {
    element: Element;
    inIsolation?: boolean;
    initialEntry?: string;
}

const mount = ({ element, initialEntry, inIsolation }: IMountProps): (() => void) => {
    const root = createRoot(element);
    // NOTE: React.StrictMode breaks React Router
    root.render(<App inIsolation={!!inIsolation} initialEntry={initialEntry} />);

    // If you want to start measuring performance in your app, pass a function
    // to log results (for example: reportWebVitals(console.log))
    // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
    reportWebVitals();

    return (): void => {
        console.log('Unmounting Remote Example App...');
        // NOTE: This should be done for all MF's to  avoid memory leaks
        hostNavigateObservable.unsubscribeAll();
    };
};

if (process.env.NODE_ENV === 'development') {
    const element = document.querySelector('#remote-example-app-root');

    if (element) {
        mount({ element, inIsolation: true });
    }
}

// ts-unused-exports:disable-next-line
export { mount };
