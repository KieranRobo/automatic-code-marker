import React from 'react';
import { Accordion, Card, Button, Table } from 'react-bootstrap';

const TestCaseResults = props => {

    return (
        <Accordion>
            <Table width="100%" bordered size="sm">
                <thead>
                    <tr>
                        <th width="30%">Test Input</th>
                        <th width="35%">Expected Output</th>
                        <th width="35%">Actual Output</th>
                    </tr>
                </thead>
                <tbody>
                {
                    props.testCases.map(testCase => {
                        var color = ('white');
                        

                        let resultOutput = ('None');
                        let rowClass = ('blankResultRow');
                        if (props.results != null) {
                            props.results.map(result => {
                                if (testCase.id === result.id) {
                                    resultOutput = result.output;
                                    if (result.success) rowClass = ('successResultRow')
                                    else rowClass = ('failResultRow')
                                }
                            })
                        }

                        

                        
                        return (
                                    <tr key={testCase.id} class={rowClass}>
                                        <td>
                                            <Table borderless size="sm">
                                                <tbody>
                                                {
                                                    testCase.arguments.map(argument => {
                                                        return (
                                                            <tr class={rowClass}>
                                                                <td width="40%">{argument.name}</td>
                                                                <td width="60%">{argument.value}</td>
                                                            </tr>
                                                        )
                                                    })
                                                }
                                                </tbody>
                                            </Table>
                                        </td>
                                        <td>
                                            {testCase.expected_result}
                                        </td>
                                        <td>
                                            {resultOutput}
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