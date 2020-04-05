import React, { useState } from 'react';
import { Route, Switch, Link, BrowserRouter } from 'react-router-dom';

import {Button, Navbar, Nav, Form, FormControl} from 'react-bootstrap';

import Home from './Home';
import Challenges from './Challenges';
import Classes from './classes/Classes';
import ClassDetails from './classes/ClassDetails';
import ChallengeSolver from './challenges/ChallengeSolver';
import NewChallenge from './NewChallenge';
import Login from './Login';
import Logout from './Logout';
import Register from './Register';

import * as firebase from 'firebase/app';
import firebaseConfig from './firebase/firebaseConfig';
import NewClass from './classes/NewClass';
import Submissions from './classes/Submissions';


const firebaseApp = firebase.initializeApp(firebaseConfig);

function logoutinButton() {
  if (localStorage.getItem('lecturerId') == null && localStorage.getItem('studentId') == null) {
    return (
      <Button variant="outline-info" href="/login">Login</Button>
    )
  } else {
    return (
      <Button variant="outline-info" href="/logout">Logout</Button>
    )
  }
}

function createChallengeLink() {
  if (localStorage.getItem('lecturerId') != null) {
    return (
      <Nav.Link href="/challenges/new">Create Challenge</Nav.Link>
    )
  }
}

function classesLink() {
  if (localStorage.getItem('lecturerId') != null) {
    return (
      <Nav.Link href="/classes">Classes</Nav.Link>
    )
  }
}

function App() {
  const [isAuthenticated, userHasAuthenticated] = useState(false);

  return (
    <div className="App">
      

      <>
        <Navbar bg="dark" variant="dark">
          <Navbar.Brand href="/">Automatic Code Marker</Navbar.Brand>
          <Nav className="mr-auto">
            <Nav.Link href="/challenges">View Challenges</Nav.Link>
            { createChallengeLink() }
            { classesLink() }
          </Nav>
          <Form inline>
          { logoutinButton() }
          </Form>
        </Navbar>
        <br/>
        
      </>
       
      <Switch>
        
          <Route exact path="/" component={Home} />

          <Route appProps={{ isAuthenticated, userHasAuthenticated }} exact path="/login" component={Login} />
          <Route appProps={{ isAuthenticated, userHasAuthenticated }} exact path="/logout" component={Logout} />

          <Route exact path="/register" component={Register} />
          <Route exact path="/classes" component={Classes} />
          <Route exact path="/classes/create" component={NewClass} />
          <Route exact path="/classes/:id" component={ClassDetails} />
          <Route exact path="/classes/:classId/submissions/:chalId" component={Submissions} />
          <Route exact path="/challenges" component={Challenges} />
          <Route exact path="/challenges/new" component={NewChallenge} />
          <Route exact path="/challenges/:id" component={ChallengeSolver} />
       
      </Switch>

    
    </div>
  );
}

export default App;
