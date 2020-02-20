import React from 'react';
import axios from "../../api.service";
import ChallengeDescription from './solver/ChallengeDescription';

import {Form, Button} from 'react-bootstrap';
import TestCaseResults from './solver/TestCaseResults';
import SubmissionResult from './solver/SubmissionResult';

import { Redirect } from "react-router-dom";

import AceEditor from 'react-ace';

class ChallengeSolver extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            challenge: null,
            submission: null,
            testCaseResults: null,
            result: []
        };

        this.codeChange = this.codeChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }  

    componentDidMount () {
        axios.Challenges.details(this.props.match.params.id)
        .then(response => {
            const newState = Object.assign({}, this.state, {
                challenge: response.data,
                submission: response.data.default_code
            });
        
        this.setState(newState);
        });
    }

    codeChange(newCode) {
        this.setState({ submission: newCode });
    }

    handleSubmit(event) {
        event.preventDefault();

        let studentId = localStorage.getItem("studentId");
        if (studentId == null) {
            // Lecturer submission
            studentId = -1;
        }

        axios.Challenges.submit(this.state.challenge.id, this.state.submission, "PYTHON", studentId)
        .then(response => {
            var testCasesPassed = (this.hasPassedTestCases(response.data) ? true : false);
            const newState = Object.assign({}, this.state, {
                testCaseResults: response.data,
                result : (testCasesPassed ? "SUCCESS" : "TEST_CASES_FAILURE")
            });
            this.setState(newState);
        }).catch((err) => {
            if (err.response) {
                if (err.response.status === 400) {
                    const newState = Object.assign({}, this.state, {
                        testCaseResults: null,
                        result: "COMPILATION_ERROR"
                    });
                    this.setState(newState);
                } else {
                    const newState = Object.assign({}, this.state, {
                        testCaseResults: null,
                        result: "UNKNOWN_ERROR"
                    });
                    this.setState(newState);
                }
            }
        });
        
        
    }

    hasPassedTestCases(testCaseResult) {
        var failures = 0;
        testCaseResult.forEach(function(item) {
            if (!item.success) failures++;
        });
        return ((failures > 0) ? false : true);
        
    }

    render() {
        if (localStorage.getItem('lecturerId') == null && localStorage.getItem('studentId') == null) {
            return (<Redirect to='/login' />)
        }
        if (this.state.challenge == null) {
            return (<div>Loading...</div>)
        }
        return (
        <div>
            <h2>{this.state.challenge.name}</h2>
            
            <div>
                <Form onSubmit={this.handleSubmit}>
                    <ChallengeDescription challenge={this.state.challenge}></ChallengeDescription>
                    <hr/>
                    
                    <h4>Solver</h4>
                    <div>
                        Write your code here to solve the challenge:
                    </div><br></br>
                    <AceEditor fontSize="16px" width="100%" height="250px" mode="python" theme="github" editorProps={{ $blockScrolling: true }}
                    value={this.state.submission}
                    onChange={this.codeChange}
                />
                    <div>Test cases will be ran against the <strong>{this.state.challenge.test_cases[0].method_name}</strong> method</div>
                    <Button type="submit" size="lg">Submit Code</Button>

                    <hr/>
                    <h4>Test Cases</h4><br/>
                    <SubmissionResult result={this.state.result}></SubmissionResult>
                    <TestCaseResults testCases={this.state.challenge.test_cases} results={this.state.testCaseResults}></TestCaseResults>
                </Form>
            </div>
        </div>
        );
    }
}


export default ChallengeSolver;