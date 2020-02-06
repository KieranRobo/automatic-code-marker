import React from 'react';
import { Link } from 'react-router-dom';
import { Table, Form } from 'react-bootstrap';

const YourClassesTable = props => {
    if (!props.classes) {
        return (
            <div>Loading...</div>
        )
    }
    return (
        <Table striped>
            <thead>
                <tr>
                    <td><strong>Class Code</strong></td>
                    <td><strong>Class Name</strong></td>
                    <td><strong>Students</strong></td>
                </tr>
            </thead>
            <tbody>
                
                {
                    props.classes.map(c => {
                        return (
                                <tr key={c.id}>
                                    <td>{c.class_code}</td>
                                    <td>{c.name}</td>
                                    <td>{c.students.length}</td>
                                </tr>
                        );
                    })
                }
                
            </tbody>
        </Table>
    );
};

export default YourClassesTable;