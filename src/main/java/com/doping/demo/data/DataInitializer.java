package com.doping.demo.data;


import com.doping.demo.entity.Exam;
import com.doping.demo.entity.Question;
import com.doping.demo.entity.exam_question.ExamQuestion;
import com.doping.demo.entity.exam_question.ExamQuestionId;
import com.doping.demo.repository.ExamRepository;
import com.doping.demo.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {


    @Bean
    CommandLineRunner initializeDatabase(ExamRepository examRepository, QuestionRepository questionRepository) {
        return args -> {
            Question question1 = new Question("3+5", List.of("A)4", "B)5", "C)6", "D)7", "E)8"), 4);
            Question question2 = new Question("3x5", List.of("A)10", "B)11", "C)12", "D)13", "E)14"), 2);
            Question question3 = new Question("3-5", List.of("A)-1", "B)-2", "C)-3", "D)-4", "E)-5"), 1);
            Question question4 = new Question("6/3", List.of("A)2", "B)1", "C)3", "D)4", "E)8"), 0);

            Exam exam1 = new Exam("Maths");
            Exam exam2 = new Exam("Maths2");
            Exam exam3 = new Exam("Maths3");


            exam1.addQuestion(new ExamQuestion(new ExamQuestionId(question1.getId(), exam1.getId()), exam1, question1));
            exam1.addQuestion(new ExamQuestion(new ExamQuestionId(question2.getId(), exam1.getId()), exam1, question2));
            exam1.addQuestion(new ExamQuestion(new ExamQuestionId(question3.getId(), exam1.getId()), exam1, question3));

            exam2.getExamQuestions().addAll(List.of(
                    new ExamQuestion(new ExamQuestionId(question2.getId(), exam2.getId()), exam2, question2),
                    new ExamQuestion(new ExamQuestionId(question3.getId(), exam2.getId()), exam2, question3)
            ));

            exam3.getExamQuestions().add(
                    new ExamQuestion(new ExamQuestionId(question4.getId(), exam3.getId()), exam3, question4));

            //questionRepository.saveAll(List.of(question1, question2, question3, question4));
            examRepository.saveAll(List.of(exam1, exam2, exam3));

        };
    }
}
