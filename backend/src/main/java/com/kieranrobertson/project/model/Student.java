package com.kieranrobertson.project.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student extends User {
}
