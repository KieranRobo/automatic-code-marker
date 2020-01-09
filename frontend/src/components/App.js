import React from 'react';
import { Route, Switch, Link } from 'react-router-dom';

import Button from 'react-bootstrap/Button';

import Home from './Home';
import Challenges from './Challenges';
import ChallengeSolver from './challenges/ChallengeSolver';

function App() {
  return (
    <div className="App">
      

      
        <Link to="/"><Button variant="secondary">Home</Button></Link>
        <Link to="/challenges"><Button variant="secondary">Challenges</Button></Link>
      
        
      <Switch>
        <Route exact path="/" component={Home} />
        <Route exact path="/challenges" component={Challenges} />
        <Route exact path="/challenges/:id" component={ChallengeSolver} />
      </Switch>

    </div>
  );
}

export default App;
