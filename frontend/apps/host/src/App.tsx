import React from 'react';
import './App.css';
import { Button } from 'ui';
import AsyncPeopleApp from './remotes/AsyncPeopleApp';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <h1>Host App</h1>
                <Button />
                <AsyncPeopleApp />
            </header>
        </div>
    );
}

export default App;
