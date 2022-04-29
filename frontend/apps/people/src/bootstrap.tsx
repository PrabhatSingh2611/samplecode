import React from 'react';
import { createRoot } from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

interface MountProps {
    el: Element;
    options?: MountPropsOptions;
}

interface MountPropsOptions {
    inIsolation?: boolean;
    basePath?: string;
}

const defaultMountPropsOptions = {
    inIsolation: false,
};

const mount = (el: Element) => {
    const root = createRoot(el!);
    root.render(<App />);

    // If you want to start measuring performance in your app, pass a function
    // to log results (for example: reportWebVitals(console.log))
    // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
    reportWebVitals();

    return (): void => {
        console.log('Unmounting People App...');
    };
};

if (process.env.NODE_ENV === 'development') {
    const el = document.querySelector('#people-app-root');

    if (el) {
        mount(el);
    }
}

export { mount };
