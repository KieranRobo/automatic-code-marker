package com.kieranrobertson.project.service;

import com.kieranrobertson.project.database.StudentsRepository;
import com.kieranrobertson.project.model.Lecturer;
import com.kieranrobertson.project.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private StudentsRepository studentsRepository;

    public void newLecturer(Lecturer lecturer) {

    }

    public void newStudent(Student student) {

    }

}
