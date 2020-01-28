import React from 'react';
import ChallengeCreatorForm from './challenges/creator/ChallengeCreatorForm'

import axios from "../api.service";

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
            challengeName: '',
            description: "",
            defaultCode: "",
            assessedMethod: {
                value: {
                    methodName: null,
                    args: []
                },
                label: ""
            },
            testCases: []

        };

        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
        this.handleDefaultCodeChange = this.handleDefaultCodeChange.bind(this);
        this.serializeSubmission = this.serializeSubmission.bind(this);

    }

    handleNameChange = (newChallengeName) => {
        this.setState({ challengeName: newChallengeName.target.value })
    }

    handleDescriptionChange = (newDescription) => {
        this.setState({ description: newDescription.target.value })
    }

    handleDefaultCodeChange = (newDefaultCode) => {
        this.setState({ defaultCode: newDefaultCode })

        // Updates assessed method list here
    }

    assessedMethodChange = (selectedAssessedMethod) => {
        this.setState({ assessedMethod: selectedAssessedMethod });

        this.state.assessedMethod.value.args.map((arg) => {
            this.setState({
                testCases: this.state.testCases.args.concat([{ name: arg, value: null }])
            })
        });
    }

    expectedResultChange = testCaseIndex => evt => {
        const newTestCases = this.state.testCases.map((testCase, idx) => {
            if (testCaseIndex !== idx) return testCase;
            return { ...testCase, expectedResult: evt.target.value };
          });

          this.setState({testCases: newTestCases});
    }

    argumentValueChange = (testCaseIndex, argumentIndex) => evt => {
        const newTestCases = this.state.testCases.map((testCase, idx) => {
            if (testCaseIndex !== idx) return testCase;

            const newArguments = testCase.args.map((arg, argIndex) => {
                if (argumentIndex !== argIndex) return arg;
                const argName = arg.name;
                
                return { ...arg, value : evt.target.value }
            });

            return { ...testCase, args: newArguments };
          });

          this.setState({testCases: newTestCases});
    }

    handleNewTestCase = () => {
        // Create the default test case with assessed method arguments before adding
        var appendableTestCase = [];
        this.state.assessedMethod.value.args.map((arg) => {
            appendableTestCase = appendableTestCase.concat([{
                name: arg,
                value: ""
            }])
        });

        // Add the fully created test case to the array of test cases
        this.setState({
            testCases: this.state.testCases.concat([{
                args: appendableTestCase,
                expectedResult: ""
            }])
        });


    }


    serializeSubmission = () => {
        const submission = {
            name: this.state.challengeName,
            description: this.state.description,
            default_code: this.state.defaultCode,
            test_cases: []
        };

        this.state.testCases.map((testCase) => {
            var submissionTestCase = {
                method_name: this.state.assessedMethod.value.methodName,
                arguments: [],
                expected_result: testCase.expectedResult
            }

            testCase.args.map((arg) => {
                submissionTestCase.arguments = submissionTestCase.arguments.concat([{
                    name: arg.name,
                    value: arg.value,
                    type: "INTEGER" // TODO: Hardcoded for now.
                }]);
            });

            submission.test_cases = submission.test_cases.concat([submissionTestCase]);
        });

        console.log("Serialized Submission: " + submission);
        return submission;
    }

    handleSubmit = (event) => {
        event.preventDefault();

        const data = this.serializeSubmission();
        axios.Challenges.new(data)
        .then(response => {
            console.log("Submission complete");
        }).catch((err) => {
        });
    }

    render() {
        return (
            <div>
                <h2>Create New Challenge:</h2>
                

                <div>
                    <Form onSubmit={this.handleSubmit}>
                        <Form.Group>
                            <Form.Label>Challenge Name</Form.Label>
                            <Form.Control type="text" value={this.state.challengeName} onChange={this.handleNameChange} />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Description</Form.Label>
                            <Form.Control as="textarea" rows="3" value={this.state.description} onChange={this.handleDescriptionChange} />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Default Code</Form.Label>
                            <AceEditor fontSize="16px" width="100%" height="150px" mode="python" theme="github" editorProps={{ $blockScrolling: true }}
                            value={this.state.defaultCode} onChange={this.handleDefaultCodeChange} />
                            <Form.Text className="text-muted">
                                Make sure to include the challenge method declaration.
                            </Form.Text>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Assessed Method</Form.Label>
                            <Select value={this.state.assessedMethod} options={methodFound} onChange={this.assessedMethodChange} />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Test Cases</Form.Label>
                            
                            {
                                this.state.testCases.map((testCase, testCaseIndex) => {
                                    return (
                                        <Card key={testCaseIndex}>
                                            <Card.Body>

                                                {
                                                    testCase.args.map((arg, argIndex) => {
                                                        return (
                                                            <Form.Group as={Row} key={argIndex}>
                                                                <Form.Label column sm={2}>
                                                                {arg.name}
                                                                </Form.Label>
                                                                <Col sm={10}>
                                                                <Form.Control type="text" placeholder={`Enter arg ${argIndex+1}`} onChange={this.argumentValueChange(testCaseIndex, argIndex)} value={arg.value} />
                                                                </Col>
                                                            </Form.Group>
                                                        );
                                                    })
                                                }

                                                <Form.Group>
                                                    <Form.Label>Expected Result</Form.Label>
                                                    <Form.Control type="text" onChange={this.expectedResultChange(testCaseIndex)} value={testCase.value} />
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