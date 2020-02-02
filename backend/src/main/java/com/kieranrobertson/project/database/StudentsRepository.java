package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("studentsRepository")
public interface StudentsRepository extends JpaRepository<Student, Integer> {
}
