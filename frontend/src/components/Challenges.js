import React from 'react';
import ChallengeTable from './challenges/ChallengeTable';
import axios from "../api.service";
import { Redirect } from "react-router-dom";
import Login from './Login';

import "react-loader-spinner/dist/loader/css/react-spinner-loader.css"
import Loader from 'react-loader-spinner'

class Challenges extends React.Component {

    state = {
        challenges: null,
        classes: null,
        assignedChallenges: []
    };

    constructor(props) {
        super(props);

        this.assignChallenge = this.assignChallenge.bind(this);
    }

    componentDidMount() {
        axios.Challenges.all()
        .then(response => {
            const newState = Object.assign({}, this.state, {
                challenges: response.data
        });
        
        this.setState(newState);
        });

        if (localStorage.getItem('lecturerId') != null) {
            axios.Lecturers.detailsById(localStorage.getItem('lecturerId'))
            .then(response => {
                const newState = Object.assign({}, this.state, {
                    classes: response.data.classes
            });
            
            this.setState(newState);
            });
        } else if (localStorage.getItem('studentId') != null) {
            axios.Students.detailsById(localStorage.getItem('studentId'))
            .then(response => {
                const newState = Object.assign({}, this.state, {
                    classes: response.data.classes
                });
                
                this.setState(newState);
                console.log(this.state);

                
            });
        }
    }

    assignChallenge = (challengeId, classId) => {
        axios.Classes.assignChallenge(challengeId, classId).then(response => {
            alert("Challenge assigned successfully.")
        });
    }



    render() {
        if (localStorage.getItem('lecturerId') == null && localStorage.getItem('studentId') == null) {
            return (<Redirect to='/login' />)
        }
        if (this.state.challenges == null) {
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
                <h2>View Challenges</h2>
                <div>Select a challenge from the list below:</div>
                <br></br>
                <ChallengeTable challenges={this.state.challenges} classes={this.state.classes} assignChallenge={this.assignChallenge}></ChallengeTable>
            </div>
            
        );
    }
}

export default Challenges;