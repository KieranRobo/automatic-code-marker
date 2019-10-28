package com.kieranrobertson.project.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="test_cases")
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {

    @Id
    @Column(name="id")
    private int id;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="challenge",nullable=false)
    private CodingChallenge codingChallenge;

    @Column(name="method_name")
    private String methodName;

    @JsonManagedReference
    @OneToMany(targetEntity = TestCaseArgument.class, mappedBy="testCase", fetch=FetchType.LAZY)
    private List<TestCaseArgument> arguments;

    // Due to current limitation, we always convert result to a String format
    @Column(name="expected_result")
    private String expectedResult;

    public TestCase(String methodName, List<TestCaseArgument> arguments, String expectedResult) {
        this.methodName = methodName;
        this.arguments = arguments;
        this.expectedResult = expectedResult;
    }

    public List<TestCaseArgument> getArguments() {
        return arguments;
    }

    public void setArguments(List<TestCaseArgument> arguments) {
        this.arguments = arguments;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @JsonIgnore
    public CodingChallenge getCodingChallenge() {
        return codingChallenge;
    }

    public void setCodingChallenge(CodingChallenge codingChallenge) {
        this.codingChallenge = codingChallenge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
