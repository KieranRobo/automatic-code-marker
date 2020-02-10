package com.kieranrobertson.project.database;

import com.kieranrobertson.project.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository("classesRepository")
public interface ClassRepository extends JpaRepository<Class, Integer> {

    @Query("SELECT myClass FROM Class myClass WHERE myClass.lecturer = :lecturerId")
    List<Class> getClassesByLecturer(@Param("lecturerId") int lecturerId);

    Optional<Class> findClassByClassCode(String classCode);
}
