import React from 'react';

const ChallengeDescription = props => {
    return ( <div dangerouslySetInnerHTML={{ __html: props.challenge.description }}></div>)
};

export default ChallengeDescription;