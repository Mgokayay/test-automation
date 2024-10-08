package com.doping.demo.service;

import com.doping.demo.dto.StudentResponse;
import com.doping.demo.entity.Student;

import java.util.List;

public interface StudentService {

    StudentResponse save(Student student);

    List<StudentResponse> findAll();

    StudentResponse findById(Long id);

    void deleteById(Long id);
}
