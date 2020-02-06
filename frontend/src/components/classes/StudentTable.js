import React from 'react';
import { Link } from 'react-router-dom';
import { Table, Form } from 'react-bootstrap';

const StudentTable = props => {
    if (!props.students) {
        return (
            <div>Loading...</div>
        )
    }
    return (
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
                    props.students.map(s => {
                        return (
                                <tr key={s.id}>
                                    <td>
                                        <Form.Check 
                                            type='checkbox'
                                            id={1}
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
    );
};

export default StudentTable;