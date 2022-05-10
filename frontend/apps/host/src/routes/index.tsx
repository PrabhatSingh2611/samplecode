import { Route, Switch } from 'react-router-dom';
import AsyncPeopleApp from 'remotes/AsyncPeopleApp';
import { Button } from 'ui';

export default function Routes() {
    return (
        <Switch>
            <Route path="/people">
                <AsyncPeopleApp />
            </Route>
            <Route exact path="/">
                <Home />
            </Route>
        </Switch>
    );
}

const Home = () => (
    <div className="HostAppHome">
        <h1>Host App</h1>
        <Button />
    </div>
);
