import React from 'react';
import { Redirect } from "react-router-dom";
import axios from "../api.service";

import "react-loader-spinner/dist/loader/css/react-spinner-loader.css"
import Loader from 'react-loader-spinner'

class Home extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            user: null
        };

    }  

    componentDidMount () {
        if (localStorage.getItem('lecturerId') != null) {
            axios.Lecturers.detailsById(localStorage.getItem('lecturerId'))
            .then(response => {
                const newState = Object.assign({}, this.state, {
                    user: response.data
                });
            
            this.setState(newState);
            });
        } else if (localStorage.getItem('studentId') != null) {
            axios.Students.detailsById(localStorage.getItem('studentId'))
            .then(response => {
                const newState = Object.assign({}, this.state, {
                    user: response.data
                });
            
            this.setState(newState);
            });
        }
    }
    
    render() {
        if (localStorage.getItem('lecturerId') == null && localStorage.getItem('studentId') == null) {
            return (<Redirect to='/login' />)
        }
        if (this.state.user == null) {
            return (
                <div align="center">
                    <Loader
                    type="ThreeDots"
                    color="grey"
                    height={300}
                    width={300}
                    timeout={3000} //3 secs
                    />
                    <h3>Loading...</h3>
                </div>)
        }
        return (
            <div>
                <h2>Welcome to the Automatic Code Marker</h2>
                <strong>You are logged in as {this.state.user.full_name}.</strong>
            <hr></hr>
            Navigate using the links above.</div>
        );
    }
}

export default Home;