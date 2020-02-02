package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("studentsRepository")
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT student from Student student WHERE student.email = :email")
    Optional<Student> findStudentByEmail(@Param("email") String email);
}
