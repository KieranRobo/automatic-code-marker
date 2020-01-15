import React from 'react';
import { Accordion, Card, Button, Table } from 'react-bootstrap';

const TestCaseResults = props => {
    if (!props.results) {
        return (<div></div>);
    }

    return (
        <Accordion>
            <Table width="100%" bordered>
                <thead>
                    <tr>
                        <th width="30%">Test Input</th>
                        <th width="35%">Expected Output</th>
                        <th width="35%">Actual Output</th>
                    </tr>
                </thead>
                <tbody>
                {
                    props.results.map(result => {
                        var color;
                        if (result.success) color = ('green')
                        else color = ('red');

                        var actualTestCase;
                        props.testCases.map(testCase => {
                            if (testCase.id === result.id) {
                                actualTestCase = testCase;
                            }
                        })

                        
                        return (
                                    <tr key={result.id} style={{ backgroundColor: color }}>
                                        <td>
                                        <Accordion.Toggle as={Button}>
                                            {
                                                actualTestCase.arguments.map(argument => {
                                                    return (
                                                        <div>{argument.name} : {argument.value}</div>
                                                    )
                                                })
                                            }
                                            </Accordion.Toggle>
                                        </td>
                                        <td>
                                            <Accordion.Toggle as={Button}>{actualTestCase.expected_result}</Accordion.Toggle>
                                        </td>
                                        <td>
                                            <Accordion.Toggle as={Button}>{result.output}</Accordion.Toggle>
                                        </td>
                                    </tr>
                        );
                    })
                }
            </tbody>
        </Table>
        </Accordion>
            
    );
};

export default TestCaseResults;