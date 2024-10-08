package com.doping.demo.service;


import com.doping.demo.dto.ExamResponse;
import com.doping.demo.entity.Exam;
import com.doping.demo.exception.CommonException;
import com.doping.demo.repository.ExamRepository;
import com.doping.demo.util.ExamDtoConversion;
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

class ExamServiceImplTest {

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private ExamServiceImpl examServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldSaveExamWhenExamNameNotExists() {
        Exam exam = new Exam();
        exam.setTestName("Maths");
        exam.setId(1L);


        when(examRepository.existsExamByName(exam.getTestName())).thenReturn(false);
        when(examRepository.save(exam)).thenReturn(exam);

        ExamResponse examResponse = examServiceImpl.save(exam);

        assertEquals("Maths", examResponse.testName());
        verify(examRepository).save(exam);
    }

    @Test
    public void itShouldThrowExceptionWhenExamNameIsExists() {
        Exam mockExam = new Exam();
        mockExam.setId(1L);
        mockExam.setTestName("Math Test");

        when(examRepository.existsExamByName(mockExam.getTestName())).thenReturn(true);

        CommonException exception = assertThrows(CommonException.class, () -> {
            examServiceImpl.save(mockExam);
        });

        assertEquals("Test Name is already exists" + mockExam.getTestName(), exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }

    @Test
    public void testFindAll() {
        Exam mockExam = new Exam();
        mockExam.setId(1L);
        mockExam.setTestName("Math Test");

        List<Exam> mockExamList = new ArrayList<>();
        mockExamList.add(mockExam);

        when(examRepository.findAll()).thenReturn(mockExamList);

        List<ExamResponse> examResponseList = ExamDtoConversion.examConvertList(mockExamList);
        List<ExamResponse> foundExams = examServiceImpl.findAll();

        assertEquals(examResponseList.size(), foundExams.size());
        verify(examRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_ExamNotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.empty());

        CommonException exception = assertThrows(CommonException.class, () -> {
            examServiceImpl.findById(1L);
        });

        assertEquals("Exam not found with given id1", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    public void testFindById_Success() {
        Exam mockExam = new Exam();
        mockExam.setId(1L);
        mockExam.setTestName("Math Test");

        when(examRepository.findById(1L)).thenReturn(Optional.of(mockExam));
        ExamResponse examResponse = ExamDtoConversion.examConvert(mockExam);

        ExamResponse foundExam = examServiceImpl.findById(1L);

        assertEquals(examResponse, foundExam);
        verify(examRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteById_ExamNotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.empty());

        CommonException exception = assertThrows(CommonException.class, () -> {
            examServiceImpl.deleteById(1L);
        });

        assertEquals("Exam not found with given id1", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    public void testDeleteById_Success() {
        Exam mockExam = new Exam();
        mockExam.setId(1L);
        mockExam.setTestName("Math Test");

        when(examRepository.findById(1L)).thenReturn(Optional.of(mockExam));

        examServiceImpl.deleteById(1L);

        verify(examRepository, times(1)).delete(mockExam);
    }


}