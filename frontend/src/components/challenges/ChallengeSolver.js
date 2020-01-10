import React from 'react';
import axios from "../../api.service";
import ChallengeDescription from './solver/ChallengeDescription';

import {Form, Button} from 'react-bootstrap';
import TestCaseResults from './solver/TestCaseResults';
import SubmissionResult from './solver/SubmissionResult';

class ChallengeSolver extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            challenge: null,
            submission: null,
            testCaseResults: null,
            result: null
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

    codeChange(event) {
        this.setState({ submission: event.target.value });
    }

    handleSubmit(event) {
        event.preventDefault();
        axios.Challenges.submit(this.state.challenge.id, this.state.submission)
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
        if (this.state.challenge == null) {
            return (<div>Loading...</div>)
        }
        return (
        <div>
            <div><strong>{this.state.challenge.name}</strong></div>
            <div><TestCaseResults results={this.state.testCaseResults}></TestCaseResults></div>
            <div>
                <Form onSubmit={this.handleSubmit}>
                    <ChallengeDescription challenge={this.state.challenge}></ChallengeDescription>
                    
                    <Form.Control as="textarea" rows="5" cols="50" name="submission" value={this.state.submission} onChange={this.codeChange}/>
                    
                    <Button type="submit" size="lg">Submit Code</Button>
                    <SubmissionResult result={this.state.result}></SubmissionResult>
                </Form>
            </div>
        </div>
        );
    }
}


export default ChallengeSolver;