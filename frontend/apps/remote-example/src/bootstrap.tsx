import React from 'react';
import { createRoot } from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { hostNavigateObservable } from './core/observable.hooks';

interface MountProps {
    element: Element;
    inIsolation?: boolean;
    initialEntry?: string;
}

const mount = ({ element, initialEntry, inIsolation }: MountProps) => {
    const root = createRoot(element!);
    // root.render(
    //     <React.StrictMode>
    //         <App inIsolation={!!inIsolation} initialEntry={initialEntry} />
    //     </React.StrictMode>
    // );
    root.render(<App inIsolation={!!inIsolation} initialEntry={initialEntry} />);

    // If you want to start measuring performance in your app, pass a function
    // to log results (for example: reportWebVitals(console.log))
    // or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
    reportWebVitals();

    return (): void => {
        console.log('Unmounting Remote Example App...');
        hostNavigateObservable?.usubscribeAll();
    };
};

if (process.env.NODE_ENV === 'development') {
    const element = document.querySelector('#remote-example-app-root');

    if (element) {
        mount({ element, inIsolation: true });
    }
}

export { mount };
