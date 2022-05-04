import React from 'react';
import { Input } from 'ui';
import { Link, Route, Switch } from 'react-router-dom';

import Router from './core/Router';
import RemoteExampleObservables from './core/RemoteExampleObservavbles';

interface AppProps {
    inIsolation: boolean;
    initialEntry?: string;
    defaultHistory?: History;
}

function App({ inIsolation, initialEntry }: AppProps) {
    return (
        <div className="RemoteExampleApp">
            <h1>Hello from Remote Example App!</h1>
            <Router inIsolation={inIsolation} initialEntry={initialEntry}>
                <RemoteExampleObservables />
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
    <header className="RemoteExampleAppHeader">
        <nav>
            <Link to="/">Home</Link> | <Link to="/details">Details</Link>
        </nav>
    </header>
);

const Home = (): JSX.Element => (
    <div className="RemoteExampleAppHome">
        <h1>Remote Example App</h1>
        <Input />
    </div>
);

const Details = (): JSX.Element => (
    <div className="RemoteExampleAppDetatils">
        <h1>Remote Example App Details</h1>
    </div>
);

export default App;
