import React from 'react';
import axios from "../../api.service";
import ChallengeDescription from './solver/ChallengeDescription';

import {Form, Button} from 'react-bootstrap';
import SubmissionResults from './solver/SubmissionResults';

class ChallengeSolver extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            challenge: null,
            submission: null,
            submissionResults: null
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
            const newState = Object.assign({}, this.state, {
                submissionResults: response.data
            });
            this.setState(newState);
        }).catch((err) => {
            console.error(err);
        });
        
        
    }

    render() {
        if (this.state.challenge == null) {
            return (<div>Loading...</div>)
        }
        return (
        <div>
            <div><strong>{this.state.challenge.name}</strong></div>
            <div><SubmissionResults results={this.state.submissionResults}></SubmissionResults></div>
            <div>
                <Form onSubmit={this.handleSubmit}>
                    <ChallengeDescription challenge={this.state.challenge}></ChallengeDescription>
                    
                    <Form.Control as="textarea" rows="5" cols="50" name="submission" value={this.state.submission} onChange={this.codeChange}/>
                    
                    <Button type="submit" size="lg">Submit Code</Button>
                </Form>
            </div>
        </div>
        );
    }
}


export default ChallengeSolver;