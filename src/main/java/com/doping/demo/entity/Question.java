package com.doping.demo.entity;


import com.doping.demo.entity.exam_question.ExamQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Question")
@Table(name = "question", schema = "doping")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text")
    private String questionText;

    @ElementCollection
    @CollectionTable(name = "option", schema = "doping")
    private List<String> options = new ArrayList<>();

    @Column(name = "correct_answer_index")
    private int correctAnswerIndex;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamQuestion> exams = new ArrayList<>();

    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
