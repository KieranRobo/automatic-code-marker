package com.kieranrobertson.project.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="challenge",nullable=false)
    private CodingChallenge codingChallenge;

    @Column(name="method_name")
    @JsonProperty("method_name")
    private String methodName;

    @JsonManagedReference
    @OneToMany(targetEntity = TestCaseArgument.class, mappedBy="testCase", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonProperty("arguments")
    private List<TestCaseArgument> arguments;

    // Due to current limitation, we always convert result to a String format
    @Column(name="expected_result")
    @JsonProperty("expected_result")
    private String expectedResult;

    public TestCase(String methodName, List<TestCaseArgument> arguments, String expectedResult) {
        this.methodName = methodName;
        this.arguments = arguments;
        this.expectedResult = expectedResult;
    }

    @Override
    public String toString(){
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return "[JSON ERROR]";
        }
    }
}
