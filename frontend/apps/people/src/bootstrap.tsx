import React from 'react';
import { createRoot, Root } from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

let root: Root | null = null;

const mount = (el: Element) => {
    // NOTE: This check is for error with inits of createRoot
    if (!root) {
        root = createRoot(el!);
    }
    root.render(<App />);

    // If you want to start measuring performance in your app, pass a function
    // to log results (for example: reportWebVitals(console.log))
    // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
    reportWebVitals();
};

if (process.env.NODE_ENV === 'development') {
    const el = document.querySelector('#people-app-root');

    if (el) {
        mount(el);
    }
}

export { mount };
