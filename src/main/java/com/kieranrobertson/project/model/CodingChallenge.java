package com.kieranrobertson.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kieranrobertson.project.commanders.CodeCommander;
import com.kieranrobertson.project.commanders.PythonCommander;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name="challenges")
@NoArgsConstructor
public class CodingChallenge {

    public enum ProgrammingLanguage {
        PYTHON
    }

    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    @JsonProperty("name")
    private String name;

    @Column(name="description")
    @JsonProperty("description")
    private String description;

    @Column(name="default_code")
    @JsonProperty("default_code")
    private String defaultCode;

    @JsonManagedReference
    @OneToMany(targetEntity = TestCase.class, mappedBy="codingChallenge", fetch=FetchType.LAZY)
    @JsonProperty("test_cases")
    private List<TestCase> testCases;

    public CodingChallenge(String name, String description, String defaultCode, List<TestCase> testCases) {
        this.name = name;
        this.description = description;
        this.defaultCode = defaultCode;
        this.testCases = testCases;
    }

    public Map<TestCase, TestResult> runCode(String code, ProgrammingLanguage language) {
        Map<TestCase, TestResult> testResults = new HashMap<>();
        for (TestCase testCase : testCases) {
            testResults.put(testCase,
                    getCommander(language, code).processTestCase(testCase)
            );
        }
        return testResults;
    }

    public boolean doesCompile(String code, ProgrammingLanguage language) {
        return getCommander(language, code).doesCompile();
    }

    private CodeCommander getCommander(ProgrammingLanguage language, String code) {
        CodeCommander codeCommander;
        if (language == ProgrammingLanguage.PYTHON) {
            codeCommander = new PythonCommander(code);
        } // Additional languages must go here
        else {
            throw new RuntimeException("Unknown programming language: " + language.toString());
        }
        return codeCommander;
    }
}
