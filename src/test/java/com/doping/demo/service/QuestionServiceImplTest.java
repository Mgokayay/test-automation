package com.doping.demo.service;

import com.doping.demo.dto.QuestionResponse;
import com.doping.demo.entity.Question;
import com.doping.demo.exception.CommonException;
import com.doping.demo.repository.QuestionRepository;
import com.doping.demo.util.QuestionDtoConversion;
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

class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionServiceImpl questionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_Success() {
        Question mockQuestion = new Question();
        mockQuestion.setId(1L);
        mockQuestion.setQuestionText("What is Java?");

        when(questionRepository.save(mockQuestion)).thenReturn(mockQuestion);
        QuestionResponse questionResponse = QuestionDtoConversion.questionConvert(mockQuestion);

        QuestionResponse savedQuestion = questionServiceImpl.save(mockQuestion);

        assertEquals(questionResponse, savedQuestion);
        verify(questionRepository, times(1)).save(mockQuestion);
    }

    @Test
    public void testFindAll() {
        Question mockQuestion = new Question();
        mockQuestion.setId(1L);
        mockQuestion.setQuestionText("What is Java?");

        List<Question> mockQuestionList = new ArrayList<>();
        mockQuestionList.add(mockQuestion);

        when(questionRepository.findAll()).thenReturn(mockQuestionList);

        List<QuestionResponse> questionResponseList = QuestionDtoConversion.questionConvertList(mockQuestionList);
        List<QuestionResponse> foundQuestions = questionServiceImpl.findAll();

        assertEquals(questionResponseList.size(), foundQuestions.size());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_QuestionNotFound() {
        when(questionRepository.findById(1L)).thenReturn(Optional.empty());

        CommonException exception = assertThrows(CommonException.class, () -> {
            questionServiceImpl.findById(1L);
        });

        assertEquals("Question not found with given id1", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    public void testFindById_Success() {
        Question mockQuestion = new Question();
        mockQuestion.setId(1L);
        mockQuestion.setQuestionText("What is Java?");

        when(questionRepository.findById(1L)).thenReturn(Optional.of(mockQuestion));
        QuestionResponse questionResponse = QuestionDtoConversion.questionConvert(mockQuestion);

        QuestionResponse foundQuestion = questionServiceImpl.findById(1L);

        assertEquals(questionResponse, foundQuestion);
        verify(questionRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteById_QuestionNotFound() {
        when(questionRepository.findById(1L)).thenReturn(Optional.empty());

        CommonException exception = assertThrows(CommonException.class, () -> {
            questionServiceImpl.deleteById(1L);
        });

        assertEquals("Question not found with given id1", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    public void testDeleteById_Success() {
        Question mockQuestion = new Question();
        mockQuestion.setId(1L);
        mockQuestion.setQuestionText("What is Java?");

        when(questionRepository.findById(1L)).thenReturn(Optional.of(mockQuestion));

        questionServiceImpl.deleteById(1L);

        verify(questionRepository, times(1)).delete(mockQuestion);
    }
}