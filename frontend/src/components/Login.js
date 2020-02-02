import React from 'react';

import withFirebaseAuth from 'react-with-firebase-auth'
import * as firebase from 'firebase/app';
import 'firebase/auth';
import firebaseConfig from './firebase/firebaseConfig';

import {Form, Button} from 'react-bootstrap';




class Login extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: ""
        }

        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
    }

    handleEmailChange = (newEmail) => {
        this.setState({ email: newEmail.target.value })
    }

    handlePasswordChange = (newPassword) => {
        this.setState({ password: newPassword.target.value })
    }

    handleLogin = (event) => {
        event.preventDefault();
        
        firebase.auth().signInWithEmailAndPassword(this.state.email, this.state.password).then(function() {
            console.log("success");
        })
        .catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;

            console.log(errorCode + " " + errorMessage);
            // ...
          });

    }

    render(){
        const {
            user
        } = this.props;

        return (
        <div className="App">
            <Form onSubmit={this.handleLogin}>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email"  value={this.state.email} onChange={this.handleEmailChange} />
                    <Form.Text>
                    We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" value={this.state.password} onChange={this.handlePasswordChange}/>
                </Form.Group>
                <Form.Group controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" label="Check me out" />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
        );
    }

}

export default Login;