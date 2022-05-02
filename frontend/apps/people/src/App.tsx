import React from 'react';
import { Input } from 'ui';
import { Link, Route, Routes } from 'react-router-dom';

import Router from './core/Router';

interface AppProps {
    inIsolation: boolean;
    initialEntry?: string;
}

function App({ inIsolation, initialEntry }: AppProps) {
    console.log('initialEntry: ', initialEntry);
    console.log('inIsolation: ', inIsolation);

    return (
        <div className="PeopleApp">
            <h1>Hello from People App!</h1>
            <Router inIsolation={inIsolation} initialEntry={initialEntry}>
                <Header />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="details" element={<Details />} />
                </Routes>
            </Router>
        </div>
    );
}

const Header = (): JSX.Element => (
    <header className="App-header">
        <nav>
            <Link to="/">Home</Link> | <Link to="details">Details</Link>
        </nav>
    </header>
);

const Home = (): JSX.Element => (
    <header className="App-header">
        <h1>People App</h1>
        <Input />
    </header>
);

const Details = (): JSX.Element => (
    <header className="App-header">
        <h1>People App Details</h1>
    </header>
);

export default App;
