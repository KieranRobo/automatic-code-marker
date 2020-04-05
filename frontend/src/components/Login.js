import React from 'react';

import * as firebase from 'firebase/app';
import 'firebase/auth';

import axios from "../api.service";

import {Form, Button} from 'react-bootstrap';
import { Redirect } from "react-router-dom";




class Login extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            success: false
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

        const email = this.state.email;
        const password = this.state.password;
        
        const current = this;

        
        firebase.auth().signInWithEmailAndPassword(email, password).then(function() {
            console.log("Firebase auth successful");
            // Firebase auth successful. Now check internal auth that user exists.
            
            axios.Lecturers.detailsByEmail(email)
            .then(response => {
                localStorage.setItem('lecturerId', response.data.id);
                current.props.history.push('/')
            }).catch((err) => {
                // No lecturer, now try student.
                console.log("Attempting student login");
                axios.Students.detailsByEmail(email)
                .then(response => {
                    localStorage.setItem('studentId', response.data.id);
                    current.props.history.push('/')
                    // TODO: Student login, better session management, etc.
                }).catch((err) => {
                    console.log("Internal auth ERROR: " + err);
                });
            });
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
            <h2>Login</h2>
            <p>Enter your login details below:</p>
            <Form onSubmit={this.handleLogin}>
                <Form.Group controlId="formBasicEmail">
                    <Form.Label><strong>Email Address</strong></Form.Label>
                    <Form.Control type="email" placeholder="Enter email"  value={this.state.email} onChange={this.handleEmailChange} />
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                    <Form.Label><strong>Password</strong></Form.Label>
                    <Form.Control type="password" placeholder="Enter password" value={this.state.password} onChange={this.handlePasswordChange}/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Login
                </Button>
            </Form>
        </div>
        );
    }

}

export default Login;