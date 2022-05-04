import React from 'react';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import { Button } from 'ui';

import './App.css';
import HostObservables from './core/HostObservables';
import AsyncPeopleApp from './remotes/AsyncPeopleApp';

function App() {
    return (
        <div className="HostApp">
            <BrowserRouter>
                <HostObservables />
                <Header />
                <Switch>
                    <Route path="/people">
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
    <header className="HostAppHeader">
        <nav>
            <Link className="HostAppLink" to="/">
                Home
            </Link>{' '}
            |{' '}
            <Link className="HostAppLink" to="/people">
                People
            </Link>
        </nav>
    </header>
);

const Home = (): JSX.Element => (
    <div className="HostAppHome">
        <h1>Host App</h1>
        <Button />
    </div>
);

export default App;
