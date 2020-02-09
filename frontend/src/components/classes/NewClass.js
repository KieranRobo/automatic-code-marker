import React from 'react';
import axios from "../../api.service";
import { Redirect } from "react-router-dom";

import {Form, Table, Button} from 'react-bootstrap';

class NewClass extends React.Component {

    state = {
        students: [],
        newClass: {
            classCode: "",
            className: "",
            students: []
        }
    };

    constructor(props) {
        super(props);

        this.handleStudentSelection = this.handleStudentSelection.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
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
            console.log("Submission complete");
        }).catch((err) => {
        });
    }

    render() {
        if (localStorage.getItem('lecturerId') == null) {
            return (<Redirect to='/login' />)
        }
        return (


            <div>
                <h2>Create New Class</h2>

                <Form onSubmit={this.handleSubmit}>
                    <Form.Group>
                        <Form.Label>Class Code</Form.Label>
                        <Form.Control type="text" value={this.state.newClass.classCode} onChange={this.handleClassCodeChange} />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Class Name</Form.Label>
                        <Form.Control type="text" value={this.state.newClass.className} onChange={this.handleClassNameChange} />
                    </Form.Group>
                

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

                <Button variant="primary" type="submit">
                    Submit
                </Button>

                </Form>
            </div>
            
        );
    }
}

export default NewClass;