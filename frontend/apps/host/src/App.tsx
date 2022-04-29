import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import { Button } from 'ui';

import './App.css';
import AsyncPeopleApp from './remotes/AsyncPeopleApp';

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Header />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="people" element={<AsyncPeopleApp />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

const Header = (): JSX.Element => (
    <header className="App-header">
        <nav>
            <Link to="/">Home</Link> | <Link to="people">People</Link>
        </nav>
    </header>
);

const Home = (): JSX.Element => (
    <header className="App-header">
        <h1>Host App</h1>
        <Button />
    </header>
);

export default App;
