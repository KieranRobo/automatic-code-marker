import React from 'react';
import { Redirect } from "react-router-dom";

import { Link } from 'react-router-dom';
import {Button, Table} from 'react-bootstrap';

import axios from "../../api.service";
import YourClassesTable from './YourClassesTable';

class Submissions extends React.Component {

    state = {
        submissions: [],
        students: [],
        challenge: {
            test_cases: []
        }
    };

    constructor(props) {
        super(props);
    }  

    componentDidMount() {
        // Get all submissions from class
        axios.Challenges.submissionsFromClass(this.props.match.params.chalId, this.props.match.params.classId)
        .then(response => {
            const newState = Object.assign({}, this.state, {
                submissions: response.data
        });
        
        this.setState(newState);
        console.log(this.state.submissions);
        });

        // Get all students for comparison
        axios.Students.all()
        .then(response => {
            const newState = Object.assign({}, this.state, {
                students: response.data
        });
        
        this.setState(newState);
        });

        // Get challenge details
        axios.Challenges.details(this.props.match.params.chalId)
        .then(response => {
            const newState = Object.assign({}, this.state, {
                challenge: response.data
        });
        
        this.setState(newState);
        });
    }

    displayTable() {
        return (
            <Table striped>
                <thead>
                    <tr>
                        <td><strong>Student Reg #</strong></td>
                        <td><strong>Student Name</strong></td>
                        <td><strong>Submission Score</strong></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                {
                    this.state.submissions.map(submission => {
                        let actualStudent = {
                            registration_number: null
                        };

                        this.state.students.map(student => {
                            if (submission.student_id == student.id) {
                                actualStudent = student;
                            }
                        });
                        
                        return (
                            <tr key={submission.id}>
                                <td>{actualStudent.registration_number}</td>
                                <td>{actualStudent.full_name}</td>
                                <td>{submission.test_passed}/{this.state.challenge.test_cases.length}</td>
                                <td>View Submission</td>
                                
                            </tr>
                        );
                    })
                }
                </tbody>
            </Table>
        );
    }

    render() {
        if (localStorage.getItem('lecturerId') == null) {
            return (<Redirect to='/login' />)
        }
        return (
            <div>
                There have been {this.state.submissions.length} submission(s).
                {this.displayTable()}
            </div>
            
        );
    }
}

export default Submissions;