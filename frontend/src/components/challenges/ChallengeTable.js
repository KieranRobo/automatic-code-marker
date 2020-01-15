import React from 'react';
import { Link } from 'react-router-dom';
import { Table } from 'react-bootstrap';

const ChallengeTable = props => {
    if (!props.challenges) {
        return (
            <div>Loading...</div>
        )
    }
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
};

export default ChallengeTable;