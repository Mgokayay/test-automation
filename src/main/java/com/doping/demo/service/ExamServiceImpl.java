package com.doping.demo.service;

import com.doping.demo.dto.ExamResponse;
import com.doping.demo.entity.Exam;
import com.doping.demo.exception.CommonException;
import com.doping.demo.repository.ExamRepository;
import com.doping.demo.util.ExamDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Autowired
    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    @CachePut(value = "exams", key = "#exam.id")
    public ExamResponse save(Exam exam) {
        if (examRepository.existsExamByName(exam.getTestName())) {
            throw new CommonException("Test Name is already exists: " + exam.getTestName(), HttpStatus.CONFLICT);
        }
        return ExamDtoConversion.examConvert(examRepository.save(exam));
    }

    @Override
    @Cacheable("exams")
    public List<ExamResponse> findAll() {
        return ExamDtoConversion.examConvertList(examRepository.findAll());
    }

    @Override
    @Cacheable(value = "exams", key = "#id")
    public ExamResponse findById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new CommonException("Exam not found with given id: " + id, HttpStatus.NOT_FOUND));
        return ExamDtoConversion.examConvert(exam);
    }

    @Override
    @CacheEvict(value = "exams", key = "#id")
    public void deleteById(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isPresent()) {
            examRepository.delete(exam.get());
        } else {
            throw new CommonException("Exam not found with given id: " + id, HttpStatus.NOT_FOUND);
        }
    }


}
