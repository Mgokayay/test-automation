package com.doping.demo.service;

import com.doping.demo.dto.StudentResponse;
import com.doping.demo.entity.Student;
import com.doping.demo.exception.CommonException;
import com.doping.demo.repository.StudentRepository;
import com.doping.demo.util.StudentDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @CachePut(value = "questions", key = "#question.id")
    public StudentResponse save(Student student) {
        return StudentDtoConversion.convertStudent(studentRepository.save(student));
    }

    @Override
    @Cacheable("questions")
    public List<StudentResponse> findAll() {
        return StudentDtoConversion.convertStudentList(studentRepository.findAll());
    }

    @Override
    @Cacheable(value = "questions", key = "#id")
    public StudentResponse findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new CommonException("Student not found with given id" + id, HttpStatus.NOT_FOUND));
        return StudentDtoConversion.convertStudent(student);
    }

    @Override
    @CacheEvict(value = "questions", key = "#id")
    public void deleteById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
        } else {
            throw new CommonException("Student not found with given id" + id, HttpStatus.NOT_FOUND);
        }


    }
}
