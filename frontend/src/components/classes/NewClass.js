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
        this.setState({ newClass: {
            classCode:  newClassCode.target.value ,
            className: this.state.newClass.className,
            students: this.state.newClass.students
        }})
    }

    handleClassNameChange = (newClassName) => {
        this.setState({ newClass: {
            classCode:  this.state.newClass.classCode,
            className: newClassName.target.value,
            students: this.state.newClass.students
        }})
    }

    handleStudentSelection(studentId) {
        console.log("run")
        if (this.state.newClass.students.indexOf(studentId) == -1) {
            console.log("CHECK");
        }
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
                </Form>

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
                                        <tr key={s.id} onClick={console.log("get")}>
                                            <td>
                                                <Form.Check type="checkbox"
                                                    id={s.id}
                                                    onClick={console.log("get")}
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
            </div>
            
        );
    }
}

export default NewClass;