package com.kieranrobertson.project.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="classes")
@NoArgsConstructor
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonProperty("id")
    private int id;

    //@JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lecturer",nullable=false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("lecturer")
    private Lecturer lecturer;


    @ManyToMany(cascade = { CascadeType.ALL }, fetch=FetchType.LAZY)
    @JoinTable(
            name = "students_in_classes",
            joinColumns = { @JoinColumn(name = "class_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("students")
    private List<Student> students;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch=FetchType.LAZY)
    @JoinTable(
            name = "assigned_challenges",
            joinColumns = { @JoinColumn(name = "class_id") },
            inverseJoinColumns = { @JoinColumn(name = "challenge_id") }
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("assigned_challenges")
    private List<CodingChallenge> assignedChallenges;

    @JsonProperty("class_code")
    private String classCode;

    @JsonProperty("name")
    private String name;
}
