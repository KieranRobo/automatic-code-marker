import React from 'react';
import ChallengeTable from './challenges/ChallengeTable';
import axios from "../api.service";
import { Redirect } from "react-router-dom";
import Login from './Login';

class Challenges extends React.Component {

    state = {
        challenges: null
    };

    componentDidMount() {
        axios.Challenges.all()
        .then(response => {
            const newState = Object.assign({}, this.state, {
                challenges: response.data
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
                <h2>View All Challenges</h2>
                <div>Select a challenge from the list below:</div>
                <ChallengeTable challenges={this.state.challenges}></ChallengeTable>
            </div>
            
        );
    }
}

export default Challenges;