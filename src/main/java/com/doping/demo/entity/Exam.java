package com.doping.demo.entity;

import com.doping.demo.entity.exam_question.ExamQuestion;
import com.doping.demo.entity.student_exam.StudentExam;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Exam")
@Table(name = "exam", schema = "doping")
public class Exam {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_name")
    private String testName;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ExamQuestion> examQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentExam> studentExams = new ArrayList<>();

    public Exam(String testName) {
        this.testName = testName;

    }

    public void addQuestion(ExamQuestion question) {
        if (!this.examQuestions.contains(question)) {
            this.examQuestions.add(question);
        }

    }


}
