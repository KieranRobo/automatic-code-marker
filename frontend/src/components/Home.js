import React from 'react';
import { Redirect } from "react-router-dom";

class Home extends React.Component {

    
    render() {
        if (localStorage.getItem('lecturerId') == null && localStorage.getItem('studentId') == null) {
            return (<Redirect to='/login' />)
        }
        return (
            <div>Home Page</div>
        );
    }
}

export default Home;