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
    private Lecturer lecturer;


    // TODO: FIX ME
    //@JsonBackReference
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch=FetchType.LAZY)
    @JoinTable(
            name = "students_in_classes",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "class_id") }
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Student> students;

    @JsonProperty("class_code")
    private String classCode;

    @JsonProperty("name")
    private String name;
}
