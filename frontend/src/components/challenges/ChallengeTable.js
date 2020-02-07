import React from 'react';
import { Link } from 'react-router-dom';
import { Table } from 'react-bootstrap';

import {Button} from 'react-bootstrap';

const ChallengeTable = props => {
    if (!props.challenges || !props.classes) {
        return (
            <div>Loading...</div>
        )
    }
    if (localStorage.getItem("lecturerId") == null) {
        return (
            <Table striped>
                <thead>
                    <tr>
                        <td><strong>Challenge Name</strong></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    
                    {
                        props.challenges.map(c => {
                            return (
                                    <tr key={c.id}>
                                        <td>{c.name}</td>
                                        <td><strong><Link to={`/challenges/${c.id}`}>Solve</Link></strong></td>
                                    </tr>
                            );
                        })
                    }
                    
                </tbody>
            </Table>
        );
    } else {
        return (
            <Table striped>
                <thead>
                    <tr>
                        <td><strong>Challenge Name</strong></td>
                        <td><strong>Assign to Class</strong></td>
                    </tr>
                </thead>
                <tbody>
                    {
                        props.challenges.map(chal => {
                            return (
                                    <tr key={chal.id}>
                                        <td><strong><Link to={`/challenges/${chal.id}`}>{chal.name}</Link></strong></td>
                                        <td>
                                            <select name={`chal${chal.id}-class`}>
                                                <option>---</option>
                                            {
                                                props.classes.map(clas => {
                                                    return (
                                                        <option value={clas.id}>{clas.class_code}</option>
                                                    )
                                                })
                                            }
                                            </select>
                                            
                                            <input type="button" value="Assign" onClick={ () => props.assignChallenge(1, chal.id)}/>
                                        </td>
                                    </tr>
                            );
                        })
                    }
                </tbody>
            </Table>
        );
    }
};

export default ChallengeTable;