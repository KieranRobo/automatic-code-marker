package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("lecturerRepository")
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

    @Query("SELECT lecturer from Lecturer lecturer WHERE lecturer.email = :email")
    Optional<Lecturer> findLecturerByEmail(@Param("email") String email);
}