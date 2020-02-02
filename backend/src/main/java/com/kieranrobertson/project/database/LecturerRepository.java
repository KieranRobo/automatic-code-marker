package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("lecturerRepository")
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
}