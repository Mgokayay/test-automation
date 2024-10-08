package com.doping.demo.entity.exam_question;

import com.doping.demo.entity.Exam;
import com.doping.demo.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "ExamQuestion")
@Table(name = "exam_question", schema = "doping")
public class ExamQuestion {

    @EmbeddedId
    private ExamQuestionId examQuestionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @MapsId("examId")
    @JoinColumn(name = "exam_id", foreignKey = @ForeignKey(name = "exam_question_exam_id_fk"))
    private Exam exam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @MapsId("questionId")
    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(name = "exam_question_question_id_fk"))
    private Question question;


}
