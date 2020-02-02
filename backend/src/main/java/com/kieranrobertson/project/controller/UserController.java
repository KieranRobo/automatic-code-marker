package com.kieranrobertson.project.controller;

import com.kieranrobertson.project.exception.UserNotFoundException;
import com.kieranrobertson.project.model.Lecturer;
import com.kieranrobertson.project.model.Student;
import com.kieranrobertson.project.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value="students", method=RequestMethod.GET)
    public List<Student> allStudents() {
        return userService.allStudents();
    }

    @RequestMapping(value="lecturers", method=RequestMethod.GET)
    public List<Lecturer> allLecturers() {
        return userService.allLecturers();
    }

    // NOTE: Doesn't show up on swagger for some reason, but works.
    @RequestMapping(value="lecturers", params="email", method=RequestMethod.GET)
    public Lecturer findLecturerByEmail(@RequestParam("email") String email) {
        Optional<Lecturer> lecturer = userService.findLecturerByEmail(email);
        if (!lecturer.isPresent()) {
            throw new UserNotFoundException("Lecturer with email " + email + " could not be found");
        } else {
            return lecturer.get();
        }
    }

    // NOTE: Doesn't show up on swagger for some reason, but works.
    @RequestMapping(value="students", params="email", method=RequestMethod.GET)
    public Student findStudentByEmail(@PathVariable("email") String email) {
        Optional<Student> student = userService.findStudentByEmail(email);
        if (!student.isPresent()) {
            throw new UserNotFoundException("Student with email " + email + " could not be found");
        } else {
            return student.get();
        }
    }

    @PostMapping("students")
    public void newStudent(@RequestBody Student student) {
        userService.newStudent(student);
    }

    @PostMapping("lecturers")
    public void newLecturer(@RequestBody Lecturer lecturer) {
        userService.newLecturer(lecturer);
    }
}
