package com.kieranrobertson.project.controller;

import com.kieranrobertson.project.model.Lecturer;
import com.kieranrobertson.project.model.Student;
import com.kieranrobertson.project.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("students")
    public List<Student> allStudents() {
        return userService.allStudents();
    }

    @GetMapping("lecturers")
    public List<Lecturer> allLecturers() {
        return userService.allLecturers();
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
