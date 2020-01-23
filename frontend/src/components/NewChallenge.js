import React from 'react';
import ChallengeCreatorForm from './challenges/creator/ChallengeCreatorForm'

import Select from 'react-select';
import {Form, Button, Card, Row, Col} from 'react-bootstrap';
import AceEditor from 'react-ace';

const methodFound = [
    { 
        value: {
            methodName: 'myFirstMethod',
            args: ['num1', 'num2']
        },
        label: 'myFirstMethod(num1, num2)'
    }
  ];

class NewChallenge extends React.Component {


    constructor(props) {
        super(props);

        this.state = {
            assessedMethod: {
                value: {
                    methodName: null,
                    args: []
                },
                label: null
            }
        };

    }

    assessedMethodChange = (selectedAssessedMethod) => {
        this.setState({ assessedMethod: selectedAssessedMethod });
      }

    render() {
        return (
            <div>
                <h2>Create New Challenge:</h2>
                

                <div>
                    <Form>
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Challenge Name</Form.Label>
                            <Form.Control type="text" />
                        </Form.Group>

                        <Form.Group controlId="formBasicPassword">
                            <Form.Label>Description</Form.Label>
                            <Form.Control as="textarea" rows="3" />
                        </Form.Group>

                        <Form.Group controlId="formBasicCheckbox">
                            <Form.Label>Default Code</Form.Label>
                            <AceEditor fontSize="16px" width="100%" height="150px" mode="python" theme="github" editorProps={{ $blockScrolling: true }} />
                            <Form.Text className="text-muted">
                                Make sure to include the challenge method declaration.
                            </Form.Text>
                        </Form.Group>

                        <Form.Group controlId="formBasicCheckbox">
                            <Form.Label>Assessed Method</Form.Label>
                            <Select options={methodFound} onChange={this.assessedMethodChange} />
                        </Form.Group>

                        <Form.Group controlId="formBasicCheckbox">
                            <Form.Label>Test Cases</Form.Label>
                            
                            <div>
                                <Card>
                                    <Card.Body>

                                    {
                                        this.state.assessedMethod.value.args.map(arg => {
                                            return (
                                                <Form.Group as={Row} controlId="formHorizontalEmail" key={arg}>
                                                    <Form.Label column sm={2}>
                                                    {arg}
                                                    </Form.Label>
                                                    <Col sm={10}>
                                                    <Form.Control type="email" placeholder="Enter arg input" />
                                                    </Col>
                                                </Form.Group>
                                            );
                                        })
                                    }

                        <Form.Group controlId="formBasicCheckbox">
                            <Form.Label>Expected Result</Form.Label>
                            <Form.Control type="text" />
                        </Form.Group>

                                    
                                    </Card.Body>

                                </Card>
                            </div>
                            <Form.Text className="text-muted">
                                Make sure to set at least 3 test cases.
                            </Form.Text>
                        </Form.Group>


                        <Button variant="primary" type="submit">
                            Submit
                        </Button>
                    </Form>
                </div>


            </div>
            
        );
    }
}

export default NewChallenge;