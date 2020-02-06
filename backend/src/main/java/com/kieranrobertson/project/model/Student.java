package com.kieranrobertson.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="students")
@NoArgsConstructor
public class Student extends User {

    //@JsonManagedReference
    @ManyToMany(mappedBy = "students", fetch=FetchType.LAZY)
    private Set<Class> classes;
}
