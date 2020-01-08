import React from 'react';
import { Link } from 'react-router-dom';

const ChallengeTable = props => {
    if (!props.challenges) {
        return (
            <div>Loading...</div>
        )
    }
    return (
        <table>
        <tbody>
            <tr>
                <td><strong>Challenge Name</strong></td>
                <td></td>
            </tr>
            {
                props.challenges.map(c => {
                    return (
                            <tr key={c.id}>
                                <td>{c.name}</td>
                                <td><Link to={`/challenges/${c.id}`}>Solve</Link></td>
                            </tr>
                    );
                })
            }
            
        </tbody>
        </table>
    );
};

export default ChallengeTable;