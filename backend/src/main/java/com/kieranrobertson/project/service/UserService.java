package com.kieranrobertson.project.service;

import com.kieranrobertson.project.database.LecturerRepository;
import com.kieranrobertson.project.database.StudentsRepository;
import com.kieranrobertson.project.model.Lecturer;
import com.kieranrobertson.project.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private StudentsRepository studentsRepository;

    @Resource
    private LecturerRepository lecturerRepository;

    public void newLecturer(Lecturer lecturer) {
        lecturerRepository.save(lecturer);
    }

    public void newStudent(Student student) {
        studentsRepository.save(student);
    }

    public List<Lecturer> allLecturers() {
        return lecturerRepository.findAll();
    }

    public List<Student> allStudents() {
        return studentsRepository.findAll();
    }

}
