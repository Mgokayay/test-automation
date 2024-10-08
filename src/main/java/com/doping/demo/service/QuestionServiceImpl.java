package com.doping.demo.service;

import com.doping.demo.dto.QuestionResponse;
import com.doping.demo.entity.Question;
import com.doping.demo.exception.CommonException;
import com.doping.demo.repository.QuestionRepository;
import com.doping.demo.util.QuestionDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    @CachePut(value = "questions", key = "#question.id")
    public QuestionResponse save(Question question) {
        return QuestionDtoConversion.questionConvert(questionRepository.save(question));
    }

    @Override
    @Cacheable("questions")
    public List<QuestionResponse> findAll() {
        return QuestionDtoConversion.questionConvertList(questionRepository.findAll());
    }

    @Override
    @Cacheable(value = "questions", key = "#id")
    public QuestionResponse findById(Long id) {
        return QuestionDtoConversion.questionConvert(questionRepository.findById(id)
                .orElseThrow(() -> new CommonException("Question not found with given id: " + id, HttpStatus.NOT_FOUND)));
    }

    @Override
    @CacheEvict(value = "questions", key = "#id")
    public void deleteById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            questionRepository.delete(question.get());
        } else {
            throw new CommonException("Question not found with given id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
