import React from 'react';

import * as firebase from 'firebase/app';
import 'firebase/auth';

import { Redirect } from "react-router-dom";

class Logout extends React.Component {

    constructor(props) {
        super(props);
        firebase.auth().signOut().then(function() {
            // TODO: obviously very poor implementation - change me.
            localStorage.clear();
            console.log("Sign out complete.");
        })
    }

    render(){
        return (<Redirect to='/login' />)
    }

}

export default Logout;