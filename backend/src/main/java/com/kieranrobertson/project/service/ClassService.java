package com.kieranrobertson.project.service;

import com.kieranrobertson.project.database.ClassRepository;
import com.kieranrobertson.project.model.Class;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Resource
    private ClassRepository classRepository;

    public Optional<Class> getClass(int classId) {
        return classRepository.findById(classId);
    }

    public void newClass(Class newClass) {
        classRepository.save(newClass);
    }

    public List<Class> getClassesForLecturer(int lecturerId) {
        return classRepository.getClassesByLecturer(lecturerId);
    }
}
