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
            challengeName: null,
            description: null,
            defaultCode: null,
            assessedMethod: {
                value: {
                    methodName: null,
                    args: []
                },
                label: null
            },
            testCases: []

        };

    }

    assessedMethodChange = (selectedAssessedMethod) => {
        this.setState({ assessedMethod: selectedAssessedMethod });

        this.state.assessedMethod.value.args.map((arg) => {
            this.setState({
                testCases: this.state.testCases.args.concat([{ name: arg, value: null }])
            })
        });
    }

    handleNewTestCase = () => {
        // Create the default test case with assessed method arguments before adding
        var appendableTestCase = [];
        this.state.assessedMethod.value.args.map((arg) => {
            appendableTestCase = appendableTestCase.concat([{
                name: arg,
                value: null
            }])
        });

        this.setState({
            testCases: this.state.testCases.concat([{
                args: appendableTestCase,
                expectedResult: null
            }])
        });


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
                            <Select value={this.state.assessedMethod} options={methodFound} onChange={this.assessedMethodChange} />
                        </Form.Group>

                        <Form.Group controlId="formBasicCheckbox">
                            <Form.Label>Test Cases</Form.Label>
                            
                            {
                                this.state.testCases.map((testCase, idx) => {
                                    return (
                                        <Card>
                                            <Card.Body>

                                                {
                                                    testCase.args.map((arg, idx) => {
                                                        return (
                                                            <Form.Group as={Row} controlId="formHorizontalEmail" key={idx}>
                                                                <Form.Label column sm={2}>
                                                                arg
                                                                </Form.Label>
                                                                <Col sm={10}>
                                                                <Form.Control type="text" placeholder={`Enter arg ${idx+1}`} />
                                                                </Col>
                                                            </Form.Group>
                                                        );
                                                    })
                                                }

                                                <Form.Group controlId="formBasicCheckbox">
                                                    <Form.Label>Expected Result</Form.Label>
                                                    <Form.Control controlId="expectedResult" type="text" />
                                                </Form.Group>

                                            </Card.Body>
                                        </Card>
                                    )
                                })
                            }
                            
                            <div>
                            <Button variant="primary" onClick={this.handleNewTestCase}>
                                Add Test Case
                            </Button></div>


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