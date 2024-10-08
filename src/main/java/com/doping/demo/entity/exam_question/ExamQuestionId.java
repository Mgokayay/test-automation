package com.doping.demo.entity.exam_question;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExamQuestionId {

    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "question_id")
    private Long questionId;
    
}
