import React from 'react';
import { Redirect } from "react-router-dom";

import { Link } from 'react-router-dom';
import {Button, Table} from 'react-bootstrap';

import axios from "../../api.service";
import YourClassesTable from './YourClassesTable';

class ClassDetails extends React.Component {

    state = {
        classDetails: null,
        assignedChallenges: []
    };

    componentDidMount() {
        axios.Classes.details(this.props.match.params.id)
        .then(response => {
            const newState = Object.assign({}, this.state, {
                classDetails: response.data
            });
        
            this.setState(newState);

            this.state.classDetails.assigned_challenges.map(c => {
                axios.Challenges.details(c)
                .then(chalResponse => {
                    this.setState({
                        assignedChallenges: this.state.assignedChallenges.concat(chalResponse.data)
                      });
                });
            });
        });
    }

    render() {
        if (localStorage.getItem('lecturerId') == null) {
            return (<Redirect to='/login' />)
        }
        if (this.state.classDetails == null) {
            return (<div>Loading...</div>)
        }
        return (
            <div>
                <h2>Class Details</h2>
                <p>{this.state.classDetails.name}</p>
                <h3>Assigned Challenges</h3>

                <Table striped>
                    <thead>
                        <tr>
                            <td><strong>Challenge Name</strong></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.assignedChallenges.map(chal => {
                                return (
                                        <tr key={chal.id}>
                                            <td><strong><Link to={`/challenges/${chal.id}`}>{chal.name}</Link></strong></td>
                                            <td>
                                                <Link to="">View Submissions</Link>
                                            </td>
                                        </tr>
                                );
                            })
                        }
                    </tbody>
                </Table>

            </div>
            
        );
    }
}

export default ClassDetails;