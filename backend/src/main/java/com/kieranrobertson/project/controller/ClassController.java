package com.kieranrobertson.project.controller;

import com.kieranrobertson.project.exception.ClassNotFoundException;
import com.kieranrobertson.project.model.Class;
import com.kieranrobertson.project.service.ClassService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping("classes")
public class ClassController {

    @Resource
    private ClassService classService;

    @GetMapping("{id}")
    public Class getClass(@PathVariable("id") int id) {
        Optional<Class> classOptional = classService.getClass(id);
        if (!classOptional.isPresent()) {
            throw new ClassNotFoundException("Class with ID " + id + " does not exist");
        } else {
            return classOptional.get();
        }
    }

    @PostMapping
    public void newClass(@RequestBody Class newClass) {
        classService.newClass(newClass);
    }
}
