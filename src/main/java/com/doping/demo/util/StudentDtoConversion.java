package com.doping.demo.util;

import com.doping.demo.dto.StudentResponse;
import com.doping.demo.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentDtoConversion {

    public static List<StudentResponse> convertStudentList(List<Student> studentList) {

        return studentList.stream().map(student -> new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getStudentNumber()
        )).collect(Collectors.toList());
    }

    public static StudentResponse convertStudent(Student student){
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getStudentNumber()
        );
    }
}
