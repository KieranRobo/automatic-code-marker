package com.kieranrobertson.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
