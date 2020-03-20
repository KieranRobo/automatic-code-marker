import React from 'react';
import { Redirect } from "react-router-dom";

import { Link } from 'react-router-dom';
import {Button, Table} from 'react-bootstrap';

import axios from "../../api.service";
import YourClassesTable from './YourClassesTable';

class ClassDetails extends React.Component {

    state = {
        classDetails: null,
        assignedChallenges: [],
        allStudents: []
    };

    componentDidMount() {
        axios.Classes.details(this.props.match.params.id)
        .then(response => {
            const newState = Object.assign({}, this.state, {
                classDetails: response.data
            });
        
            this.setState(newState);

            console.log(this.state.classDetails.students)
            // Pull student details
            this.state.classDetails.students.map(s => {
                axios.Students.detailsById(s)
                .then(student => {
                    this.setState({
                        allStudents: this.state.allStudents.concat(student.data)
                      });
                });
            });

            // Pull assigned challenge details
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

    assignedChallengesTableData() {
        if (this.state.assignedChallenges.length == 0) {
            return (
                <tr>No challenges are currently assigned to this class.</tr>
            )
        }
        let returnData = [];
        this.state.assignedChallenges.map(chal => {
            returnData.push(
                <tr key={chal.id}>
                    <td><strong><Link to={`/challenges/${chal.id}`}>{chal.name}</Link></strong></td>
                    <td>
                        <Link to={`/classes/${this.props.match.params.id}/submissions/${chal.id}`}>View Submissions</Link>
                    </td>
                </tr>
            );
        })
    
        return returnData;
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
                <table width="50%">
                    <tr>
                        <td><strong>Class Code:</strong></td>
                        <td>{this.state.classDetails.class_code}</td>
                    </tr>
                    <tr>
                        <td><strong>Class Name:</strong></td>
                        <td>{this.state.classDetails.name}</td>
                    </tr>
                </table><br></br>

                <h4>Students</h4>

                <Table striped>
                    <thead>
                        <tr>
                            <td width="30%"><strong>Registration Number</strong></td>
                            <td width="70%"><strong>Full Name</strong></td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.allStudents.map(student => {
                                return (
                                        <tr key={student.id}>
                                            <td>{student.registration_number}</td>
                                            <td>{student.full_name}</td>
                                        </tr>
                                );
                            })
                        }
                    </tbody>
                </Table>

                <h4>Assigned Challenges</h4>

                <Table striped>
                    <thead>
                        <tr>
                            <td><strong>Challenge Name</strong></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        
                        this.assignedChallengesTableData()
                    }
                    </tbody>
                </Table>

            </div>
            
        );
    }
}

export default ClassDetails;