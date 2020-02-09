import React from 'react';
import ChallengeCreatorForm from './challenges/creator/ChallengeCreatorForm'

import axios from "../api.service";

import Select from 'react-select';
import {Form, Button, Card, Row, Col, Alert} from 'react-bootstrap';
import AceEditor from 'react-ace';

import { Redirect } from "react-router-dom";

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
            testCases: [],

            detectedMethods: [],
            currentStatus: "START"
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
        //console.log(this.state.defaultCode);

        // Reset detected methods
        this.setState({
            detectedMethods: [],
            testCases: [],
            assessedMethod: {
                value: {
                    methodName: null,
                    args: []
                },
                label: ""
            }
        });

        // Updates assessed method list here
        this.state.defaultCode.split('\n').map(line => {

            // This is horrible extraction from string. Definitely needs improved.
            if (/def ([a-zA-Z0-9,_])+\(([a-zA-Z0-9,_,\,, ])*\):$/.test(line)) {
                const methodName = line.split(" ")[1].split("(")[0];

                let args = line.replace("def ", "").replace(methodName, "")
                                    .replace(" ", "")
                                    .replace("(", "")
                                    .replace(")", "")
                                    .replace(":","")
                                    .split(",");

                // Deals with there being one element of "" when no args.
                if (args[0] == "") args = [];
                                    
                // Update detected methods
                const methodDetails = { 
                    value: {
                        methodName: methodName,
                        args: args
                    },
                    label: line
                };

                this.setState({
                    detectedMethods: this.state.detectedMethods.concat([methodDetails])
                });
            }
        });
    }


    assessedMethodChange = (selectedAssessedMethod) => {
        this.setState({ assessedMethod: selectedAssessedMethod,
                        testCases: []
                    });

        /*this.state.assessedMethod.value.args.map((arg) => {
            this.setState({
                testCases: this.state.testCases.args.concat([{ name: arg, value: null }])
            })
        });*/
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

        return submission;
    }

    getAddTestCaseButton() {
        if (this.state.assessedMethod.value.methodName == null) {
            return(<Button variant="primary" onClick={this.handleNewTestCase} disabled>
                    Add Test Case
                </Button>)
        } 
        return(<Button variant="primary" onClick={this.handleNewTestCase}>
                Add Test Case
          </Button>)
        
    }

    handleSubmit = (event) => {
        event.preventDefault();

        const data = this.serializeSubmission();
        axios.Challenges.new(data)
        .then(response => {
            this.setState({currentStatus: "SUCCESS"})
        }).catch((err) => {
            this.setState({currentStatus: "FAILURE"})
        });
    }

    currentStatusBanner = () => {
        if (this.state.currentStatus == "SUCCESS") {
            return (<Alert variant="success">New Challenge Successfully Created</Alert>)
        } else if (this.state.currentStatus == "FAILURE") {
            return (<Alert variant="danger">An Error Occurred with your Submission</Alert>)
        }else {
            return (<div></div>);
        }
    }

    render() {
        if (localStorage.getItem('lecturerId') == null) {
            return (<Redirect to='/login' />)
        }
        return (
            <div>
                <h2>Create New Challenge:</h2>
                
                {
                    this.currentStatusBanner()
                }

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
                            <Select value={this.state.assessedMethod} options={this.state.detectedMethods} onChange={this.assessedMethodChange} />
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
                            {
                                this.getAddTestCaseButton()
                            }
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