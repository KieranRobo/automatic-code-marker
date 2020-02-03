package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("classesRepository")
public interface ClassesRepository extends JpaRepository<Class, Integer> {
}
