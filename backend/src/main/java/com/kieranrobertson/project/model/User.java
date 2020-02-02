package com.kieranrobertson.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonProperty("id")
    private int id;

    @Column(name="email")
    @JsonProperty("email")
    private String email;

    @Column(name="registration_number")
    @JsonProperty("registration_number")
    private String registrationNumber;

    @Column(name="full_name")
    @JsonProperty("full_name")
    private String fullName;

}
