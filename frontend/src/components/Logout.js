import React from 'react';

import * as firebase from 'firebase/app';
import 'firebase/auth';

import { Redirect } from "react-router-dom";

class Logout extends React.Component {

    constructor(props) {
        super(props);
        const current = this;
        firebase.auth().signOut().then(function() {
            // TODO: obviously very poor implementation - change me.
            localStorage.clear();
            current.props.history.push('/')
            console.log("Sign out complete.");
        })
    }

    render(){
        return (<div></div>)
    }

}

export default Logout;