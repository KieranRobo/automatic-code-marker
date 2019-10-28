package com.kieranrobertson.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="test_cases_arguments")
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseArgument {

    @Id
    @Column(name="id")
    @JsonIgnore
    private int id;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="test_case",nullable=false)
    private TestCase testCase;

    @Column(name="argument_name")
    @JsonProperty("name")
    private String argumentName;

    @Column(name="argument_type")
    @JsonProperty("type")
    private String argumentType;

    // Despite being String, this can hold any type. Parsed based on argumentType value.
    @Column(name="argument_value")
    @JsonProperty("value")
    private String argumentValue;

    public TestCaseArgument(String argumentName, String argumentType, String argumentValue) {
        this.argumentName = argumentName;
        this.argumentType = argumentType;
        this.argumentValue = argumentValue;
    }

    /**
     * Provides the 'code ready' value of the argument
     * @return code ready value of the argument.
     */
    @JsonIgnore
    public String getCodeReadyArgumentValue() {
        if (argumentType.equals("STRING")) {
            return "\""+argumentValue+"\"";
        }
        else {
            return argumentValue;
        }
    }
}
