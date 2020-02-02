package com.kieranrobertson.project.service;

import com.kieranrobertson.project.database.LecturerRepository;
import com.kieranrobertson.project.database.StudentRepository;
import com.kieranrobertson.project.model.Lecturer;
import com.kieranrobertson.project.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private LecturerRepository lecturerRepository;

    public void newLecturer(Lecturer lecturer) {
        lecturerRepository.save(lecturer);
    }

    public void newStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Lecturer> allLecturers() {
        return lecturerRepository.findAll();
    }

    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    public Optional<Lecturer> findLecturerByEmail(String email) {
        return lecturerRepository.findLecturerByEmail(email);
    }

    public Optional<Student> findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

}
