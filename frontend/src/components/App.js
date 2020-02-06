import React, { useState } from 'react';
import { Route, Switch, Link, BrowserRouter } from 'react-router-dom';

import Button from 'react-bootstrap/Button';

import Home from './Home';
import Challenges from './Challenges';
import Classes from './classes/Classes';
import ChallengeSolver from './challenges/ChallengeSolver';
import NewChallenge from './NewChallenge';
import Login from './Login';
import Register from './Register';

import * as firebase from 'firebase/app';
import firebaseConfig from './firebase/firebaseConfig';
import NewClass from './classes/NewClass';


const firebaseApp = firebase.initializeApp(firebaseConfig);

function App() {
  const [isAuthenticated, userHasAuthenticated] = useState(false);

  return (
    <div className="App">
      

      
        <Link to="/"><Button variant="secondary">Home</Button></Link>
        <Link to="/challenges"><Button variant="secondary">Challenges</Button></Link>
        <Link to="/classes"><Button variant="secondary">Your Classes</Button></Link>
        <Link to="/challenges/new"><Button variant="secondary">New Challenge</Button></Link>
      
       
      <Switch>
        
          <Route exact path="/" component={Home} />
          <Route appProps={{ isAuthenticated, userHasAuthenticated }} exact path="/login" component={Login} />
          <Route exact path="/register" component={Register} />
          <Route exact path="/classes" component={Classes} />
          <Route exact path="/classes/create" component={NewClass} />
          <Route exact path="/challenges" component={Challenges} />
          <Route exact path="/challenges/new" component={NewChallenge} />
          <Route exact path="/challenges/:id" component={ChallengeSolver} />
       
      </Switch>

    </div>
  );
}

export default App;
