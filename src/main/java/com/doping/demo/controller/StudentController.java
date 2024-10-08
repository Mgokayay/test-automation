package com.doping.demo.controller;

import com.doping.demo.dto.StudentResponse;
import com.doping.demo.entity.Student;
import com.doping.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponse addStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}
