import React from 'react';
import { Input } from 'ui';
import { Link, Route, Switch } from 'react-router-dom';

import Router from './core/Router';
import PeopleObservables from './core/PeopleObservables';

interface AppProps {
    inIsolation: boolean;
    initialEntry?: string;
    defaultHistory?: History;
}

function App({ inIsolation, initialEntry }: AppProps) {
    return (
        <div className="PeopleApp">
            <h1>Hello from People App!</h1>
            <Router inIsolation={inIsolation} initialEntry={initialEntry}>
                <PeopleObservables />
                <Header />
                <Switch>
                    <Route path="/details">
                        <Details />
                    </Route>
                    <Route path="/">
                        <Home />
                    </Route>
                </Switch>
            </Router>
        </div>
    );
}

const Header = (): JSX.Element => (
    <header className="PeopleAppHeader">
        <nav>
            <Link to="/">Home</Link> | <Link to="/details">Details</Link>
        </nav>
    </header>
);

const Home = (): JSX.Element => (
    <div className="PeopleAppHeaderHome">
        <h1>People App</h1>
        <Input />
    </div>
);

const Details = (): JSX.Element => (
    <div className="PeopleAppDetails">
        <h1>People App Details</h1>
    </div>
);

export default App;