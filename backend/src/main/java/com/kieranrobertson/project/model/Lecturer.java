package com.kieranrobertson.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="lecturers")
@Data
@NoArgsConstructor
public class Lecturer extends User {

    @JsonManagedReference
    @OneToMany(targetEntity = Class.class, mappedBy="lecturer", fetch= FetchType.LAZY)
    @JsonProperty("classes")
    private List<Class> classes;

}
