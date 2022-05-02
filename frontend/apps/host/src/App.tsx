import React from 'react';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import { Button } from 'ui';

import './App.css';
import HostObservables from './core/HostObservables';
import AsyncPeopleApp from './remotes/AsyncPeopleApp';

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <HostObservables />
                <Header />
                <Switch>
                    <Route exact path="/people">
                        <AsyncPeopleApp />
                    </Route>
                    <Route exact path="/">
                        <Home />
                    </Route>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

const Header = (): JSX.Element => (
    <header className="App-header">
        <nav>
            <Link to="/">Home</Link> | <Link to="/people">People</Link>
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
