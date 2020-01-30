package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("studentsRepository")
public interface StudentsRepository extends CrudRepository<Student, Integer> {
}
