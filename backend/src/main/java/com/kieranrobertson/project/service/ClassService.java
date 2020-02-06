package com.kieranrobertson.project.service;

import com.kieranrobertson.project.database.ClassRepository;
import com.kieranrobertson.project.database.LecturerRepository;
import com.kieranrobertson.project.database.StudentRepository;
import com.kieranrobertson.project.exception.UserNotFoundException;
import com.kieranrobertson.project.model.Class;
import com.kieranrobertson.project.model.Lecturer;
import com.kieranrobertson.project.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Resource
    private ClassRepository classRepository;

    @Resource
    private LecturerRepository lecturerRepository;

    @Resource
    private StudentRepository studentRepository;

    public Optional<Class> getClass(int classId) {
        return classRepository.findById(classId);
    }

    public void newClass(String classCode, String name, int lecturerId, int[] studentIds) {
        Class newClass = new Class();
        newClass.setClassCode(classCode);
        newClass.setName(name);

        // Set actual lecturer
        Optional<Lecturer> actualLecturer = lecturerRepository.findById(lecturerId);
        if (!actualLecturer.isPresent()) {
            throw new UserNotFoundException("Lecturer with ID " + lecturerId + " does not exist.");
        } else {
            newClass.setLecturer(actualLecturer.get());
        }

        // Set actual students
        List<Student> students = new ArrayList<>();
        for (int studentId : studentIds) {
            Optional<Student> actualStudent = studentRepository.findById(studentId);
            if (!actualStudent.isPresent()) {
                throw new UserNotFoundException("Student with ID " + studentId + " does not exist.");
            } else {
                students.add(actualStudent.get());
                System.out.println(actualStudent.get().getId());
            }
        }
        newClass.setStudents(students);

        classRepository.save(newClass);
    }

    public List<Class> getClassesForLecturer(int lecturerId) {
        return classRepository.getClassesByLecturer(lecturerId);
    }
}
