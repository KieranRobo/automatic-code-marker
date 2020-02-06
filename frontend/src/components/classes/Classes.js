import React from 'react';
import { Redirect } from "react-router-dom";

import { Link } from 'react-router-dom';
import Button from 'react-bootstrap/Button';

import axios from "../../api.service";
import YourClassesTable from './YourClassesTable';

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
        return (
            <div>
                <h2>Your Classes</h2>
                <Link to="/classes/create"><Button variant="secondary">Create New Class</Button></Link>
                <YourClassesTable classes={this.state.classes} />
            </div>
            
        );
    }
}

export default Classes;