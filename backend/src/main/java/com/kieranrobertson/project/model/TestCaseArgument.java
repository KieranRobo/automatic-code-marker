package com.kieranrobertson.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="test_cases_arguments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestCaseArgument {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="test_case",nullable=false)
    private TestCase testCase;

    @Column(name="argument_name")
    @JsonProperty("name")
    private String argumentName;

    // Despite being String, this can hold any type. Parsed based on argumentType value.
    @Column(name="argument_value")
    @JsonProperty("value")
    private String argumentValue;

    public TestCaseArgument(String argumentName, String argumentValue) {
        this.argumentValue = argumentValue;
    }
}
