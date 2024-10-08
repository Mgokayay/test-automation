package com.doping.demo.service;

import com.doping.demo.dto.StudentResponse;
import com.doping.demo.entity.Student;
import com.doping.demo.exception.CommonException;
import com.doping.demo.repository.StudentRepository;
import com.doping.demo.util.StudentDtoConversion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_Success() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");

        when(studentRepository.save(mockStudent)).thenReturn(mockStudent);
        StudentResponse studentResponse = StudentDtoConversion.convertStudent(mockStudent);

        StudentResponse savedStudent = studentServiceImpl.save(mockStudent);

        assertEquals(studentResponse, savedStudent);
        verify(studentRepository, times(1)).save(mockStudent);
    }

    @Test
    public void testFindAll() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");

        List<Student> mockStudentList = new ArrayList<>();
        mockStudentList.add(mockStudent);

        when(studentRepository.findAll()).thenReturn(mockStudentList);

        List<StudentResponse> studentResponseList = StudentDtoConversion.convertStudentList(mockStudentList);
        List<StudentResponse> foundStudents = studentServiceImpl.findAll();

        assertEquals(studentResponseList.size(), foundStudents.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_StudentNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        CommonException exception = assertThrows(CommonException.class, () -> {
            studentServiceImpl.findById(1L);
        });

        assertEquals("Student not found with given id1", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    public void testFindById_Success() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(mockStudent));
        StudentResponse studentResponse = StudentDtoConversion.convertStudent(mockStudent);

        StudentResponse foundStudent = studentServiceImpl.findById(1L);

        assertEquals(studentResponse, foundStudent);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteById_StudentNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        CommonException exception = assertThrows(CommonException.class, () -> {
            studentServiceImpl.deleteById(1L);
        });

        assertEquals("Student not found with given id1", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    public void testDeleteById_Success() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(mockStudent));

        studentServiceImpl.deleteById(1L);

        verify(studentRepository, times(1)).delete(mockStudent);
    }
}