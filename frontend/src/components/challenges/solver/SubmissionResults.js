import React from 'react';
import { Accordion, Card, Button } from 'react-bootstrap';

const SubmissionResults = props => {
    if (!props.results) {
        return (<div>Results here</div>);
    }

    return (
        <Accordion>
        {
            props.results.map(result => {
                var color;
                if (result.success) color = ('green')
                else color = ('red');
                
                return (
                    <Card key={result.id} style={{ backgroundColor: color }}>
                        <Card.Header>
                            <Accordion.Toggle as={Button} variant="link">
                                {result.id}
                            </Accordion.Toggle>
                        </Card.Header>
                    </Card>
                );
            })
        }
        </Accordion>
            
    );
};

export default SubmissionResults;