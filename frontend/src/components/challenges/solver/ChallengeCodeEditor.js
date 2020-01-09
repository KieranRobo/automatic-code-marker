import React from 'react';
import { Form } from 'react-bootstrap';

const ChallengeCodeEditor = props => {
    return (
        <table>
        <tbody>
            <tr>
                <td><strong>Test Case ID</strong></td>
                <td>Result</td>
            </tr>
            {
                props.results.map(c => {
                    return (
                            <tr key={c.id}>
                                <td>{c.id}</td>
                                <td>{c.success}</td>
                            </tr>
                    );
                })
            }
            
        </tbody>
        </table>
    );
};

export default ChallengeCodeEditor;