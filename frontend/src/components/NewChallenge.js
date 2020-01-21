import React from 'react';
import ChallengeCreatorForm from './challenges/creator/ChallengeCreatorForm'

class NewChallenge extends React.Component {


    render() {
        return (
            <div>
                <div>Create a new coding challenge here:</div>
                <ChallengeCreatorForm />
            </div>
            
        );
    }
}

export default NewChallenge;