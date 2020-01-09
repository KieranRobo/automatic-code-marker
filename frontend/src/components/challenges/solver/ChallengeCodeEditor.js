import React from 'react';
import { Form } from 'react-bootstrap';

const ChallengeCodeEditor = props => {
    return ( 
        <div>
            <Form.Control as="textarea" rows="3" value={props.defaultCode} />
        </div>
        )
};

export default ChallengeCodeEditor;