package com.doping.demo.entity.student_exam;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentExamId {

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "exam_id")
    private Long examId;
}
