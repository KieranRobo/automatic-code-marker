package com.kieranrobertson.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping("students")
    public void newStudent() {

    }

    @PostMapping("lecturers")
    public void newLecturer() {

    }
}
