import React from 'react';
import { createRoot } from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

interface MountProps {
    element: Element;
    inIsolation?: boolean;
    initialEntry?: string;
}

const mount = ({ element, initialEntry, inIsolation }: MountProps) => {
    const root = createRoot(element!);
    root.render(<App inIsolation={!!inIsolation} initialEntry={initialEntry} />);

    // If you want to start measuring performance in your app, pass a function
    // to log results (for example: reportWebVitals(console.log))
    // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
    reportWebVitals();

    return (): void => {
        console.log('Unmounting People App...');
    };
};

if (process.env.NODE_ENV === 'development') {
    const element = document.querySelector('#people-app-root');

    if (element) {
        mount({ element, inIsolation: true });
    }
}

export { mount };
