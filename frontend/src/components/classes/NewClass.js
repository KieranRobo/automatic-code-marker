import React from 'react';
import axios from "../../api.service";
import { Redirect } from "react-router-dom";

import {Form, Table, Button, Alert} from 'react-bootstrap';

import "react-loader-spinner/dist/loader/css/react-spinner-loader.css"
import Loader from 'react-loader-spinner'

class NewClass extends React.Component {

    state = {
        students: [],
        newClass: {
            classCode: "",
            className: "",
            students: []
        },
        currentStatus: "START"
    };

    constructor(props) {
        super(props);

        this.handleStudentSelection = this.handleStudentSelection.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        console.log("we here")
        axios.Students.all()
        .then(response => {
            const newState = Object.assign({}, this.state, {
                students: response.data
        });
        
        this.setState(newState);
        });
    }

    handleClassCodeChange = (newClassCode) => {
        this.setState({ newClass: { ...this.state.newClass, classCode: newClassCode.target.value} });
    }

    handleClassNameChange = (newClassName) => {
        this.setState({ newClass: { ...this.state.newClass, className: newClassName.target.value} });
    }

    handleStudentSelection(studentId) {
        var inArray = this.state.newClass.students.indexOf(studentId);
        if (inArray == -1) {
            console.log("CHECK");
            this.setState({ newClass: { ...this.state.newClass, students: this.state.newClass.students.concat(studentId)} });

        } else {
            console.log("UNCHECK");
            this.state.newClass.students.splice(inArray, 1);
            
        }
    }

    serializeNewClass = () => {
        const newClassState = this.state.newClass;
        return {
            'class_code': newClassState.classCode,
            'name': newClassState.className,
            'lecturer_id': localStorage.getItem('lecturerId'),
            'student_ids': newClassState.students
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();

        const data = this.serializeNewClass();
        console.log(data);
        axios.Classes.new(this.serializeNewClass())
        .then(response => {
            if (response.data.code == "SUCC001") {
                this.setState({currentStatus: "SUCCESS"})
            } else if (response.data.code == "ERR001") {
                this.setState({currentStatus: "CLASS_CODE_ALREADY_EXISTS"})
            }
        }).catch((err) => {
            this.setState({currentStatus: "FAILURE"})
        });
    }

    currentStatusBanner = () => {
        if (this.state.currentStatus == "SUCCESS") {
            return (<Alert variant="success">New Class Successfully Created</Alert>)
        } else if (this.state.currentStatus == "CLASS_CODE_ALREADY_EXISTS") {
            return (<Alert variant="warning">A class with the same class code already exists. Please enter a new one.</Alert>)
        } else if (this.state.currentStatus == "FAILURE") {
            return (<Alert variant="danger">An Error Occurred with your Submission</Alert>)
        }else {
            return (<div></div>);
        }
    }
    
    allStudentsTable = () => {
        this.state.students.map(s => {
            return (
                    <tr key={s.id}>
                        <td>
                            <Form.Check type="checkbox"
                                id={s.id}
                                onClick={() => {this.handleStudentSelection(s.id)}}
                            />
                        </td>
                        <td>{s.registration_number}</td>
                        <td>{s.full_name}</td>
                        <td>{s.email}</td>
                    </tr>
            );
        })
    }

    render() {
        if (localStorage.getItem('lecturerId') == null) {
            return (<Redirect to='/login' />)
        }
        if (this.state.students.length == 0) {
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
                <h2>Create New Class</h2>
                {
                    this.currentStatusBanner()
                }

                <Form onSubmit={this.handleSubmit}>
                    <Form.Group>
                        <Form.Label><strong>Class Code</strong></Form.Label>
                        <Form.Control type="text" value={this.state.newClass.classCode} onChange={this.handleClassCodeChange} />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label><strong>Class Name</strong></Form.Label>
                        <Form.Control type="text" value={this.state.newClass.className} onChange={this.handleClassNameChange} />
                    </Form.Group>
                
                    <Form.Group>
                        <Form.Label><strong>Students</strong></Form.Label>
                        <Table striped>
                            <thead>
                                <tr>
                                    <td></td>
                                    <td><strong>Registration #</strong></td>
                                    <td><strong>Full Name</strong></td>
                                    <td><strong>Email</strong></td>
                                </tr>
                            </thead>
                            <tbody>
                                
                            
                                {
                                    this.state.students.map(s => {
                                        return (
                                                <tr key={s.id}>
                                                    <td>
                                                        <Form.Check type="checkbox"
                                                            id={s.id}
                                                            onClick={() => {this.handleStudentSelection(s.id)}}
                                                        />
                                                    </td>
                                                    <td>{s.registration_number}</td>
                                                    <td>{s.full_name}</td>
                                                    <td>{s.email}</td>
                                                </tr>
                                        );
                                    })
                                }
                                
                            </tbody>
                        </Table>
                    </Form.Group>

                

                <Button variant="primary" type="submit">
                    Submit
                </Button>

                </Form>
            </div>
            
        );
    }
}

export default NewClass;