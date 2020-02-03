package com.kieranrobertson.project.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name="students")
public class Student extends User {

    @ManyToMany(mappedBy = "students", fetch=FetchType.LAZY)
    private Set<Class> classes;
}
