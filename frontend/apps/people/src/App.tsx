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
                    <Route path="/people/details">
                        <Details />
                    </Route>
                    <Route path="/people">
                        <Home />
                    </Route>
                </Switch>
            </Router>
        </div>
    );
}

const Header = (): JSX.Element => (
    <header className="App-header">
        <nav>
            <Link to="/people">Home</Link> | <Link to="/people/details">Details</Link>
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
