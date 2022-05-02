import React from 'react';
import { Input } from 'ui';
import { Link, Route, Switch } from 'react-router-dom';

import Router from './core/Router';
import AuthObservables from './core/AuthObservavbles';

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
                <AuthObservables />
                <Header />
                <Switch>
                    <Route path="/remote-example/details">
                        <Details />
                    </Route>
                    <Route path="/remote-example">
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
            <Link to="/remote-example">Home</Link> | <Link to="/remote-example/details">Details</Link>
        </nav>
    </header>
);

const Home = (): JSX.Element => (
    <header className="App-header">
        <h1>Remote Example App</h1>
        <Input />
    </header>
);

const Details = (): JSX.Element => (
    <header className="App-header">
        <h1>Remote Example App Details</h1>
    </header>
);

export default App;
