import React from 'react';
import { Redirect } from "react-router-dom";

import { Link } from 'react-router-dom';
import Button from 'react-bootstrap/Button';

import axios from "../../api.service";
import YourClassesTable from './YourClassesTable';

import "react-loader-spinner/dist/loader/css/react-spinner-loader.css"
import Loader from 'react-loader-spinner'

class Classes extends React.Component {

    state = {
        classes: null
    };

    componentDidMount() {
        axios.Lecturers.detailsById(localStorage.getItem('lecturerId'))
        .then(response => {
            const newState = Object.assign({}, this.state, {
                classes: response.data.classes
        });
        
        this.setState(newState);
        });
    }

    render() {
        if (localStorage.getItem('lecturerId') == null) {
            return (<Redirect to='/login' />)
        }
        if (this.state.classes == null) {
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
                <h2>Your Classes</h2>
                <Link to="/classes/create"><Button variant="secondary">Create New Class</Button></Link><br/><br/>
                <YourClassesTable classes={this.state.classes} />
            </div>
            
        );
    }
}

export default Classes;