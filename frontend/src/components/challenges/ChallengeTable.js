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
        let assignedChallenges = [];
        props.classes.map(clas => {
            assignedChallenges = assignedChallenges.concat(clas.assigned_challenges)
        });
        return (
            <div>
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
                            props.challenges.map(c => {
                                if (assignedChallenges.indexOf(c.id) >= 0) {
                                    return (
                                            <tr key={c.id}>
                                                <td>{c.name}</td>
                                                <td><strong><Link to={`/challenges/${c.id}`}>Solve</Link></strong></td>
                                            </tr>
                                    );
                                }
                            })
                        }
                        
                    </tbody>
                </Table>
            </div>
        );
    } else {
        return (
            <div>
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
                                            <td id={`${chal.id}-assign`}>
                                                <select id={`chal${chal.id}-class`}>
                                                    <option>---</option>
                                                {
                                                    props.classes.map(clas => {
                                                        return (
                                                            <option value={clas.id}>{clas.class_code}</option>
                                                        )
                                                    })
                                                }
                                                </select>
                                                
                                                <input type="button" value="Assign" onClick={ () => props.assignChallenge(document.getElementById(`chal${chal.id}-class`).value, chal.id)}/>
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
};

export default ChallengeTable;