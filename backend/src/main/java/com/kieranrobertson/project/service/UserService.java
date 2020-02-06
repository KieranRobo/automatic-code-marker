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

    /*
    TODO: Potential use of template method design pattern here?
     */
    public void newLecturer(String email, String fullName, String registrationNumber) {
        Lecturer newLecturer = new Lecturer();
        newLecturer.setEmail(email);
        newLecturer.setFullName(fullName);
        newLecturer.setRegistrationNumber(registrationNumber);

        lecturerRepository.save(newLecturer);
    }

    public void newStudent(String email, String fullName, String registrationNumber) {
        Student newStudent = new Student();
        newStudent.setEmail(email);
        newStudent.setFullName(fullName);
        newStudent.setRegistrationNumber(registrationNumber);

        studentRepository.save(newStudent);
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

    public Optional<Lecturer> findLecturerById(int id) {
        return lecturerRepository.findById(id);
    }

    public Optional<Student> findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    public Optional<Student> findStudentById(int id) {
        return studentRepository.findById(id);
    }

}
