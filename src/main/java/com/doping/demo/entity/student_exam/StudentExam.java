package com.doping.demo.entity.student_exam;

import com.doping.demo.entity.Exam;
import com.doping.demo.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student_exam", schema = "doping")
public class StudentExam {

    @EmbeddedId
    private StudentExamId studentExamId;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "student_exam_student_id_fk"))
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("examId")
    @JoinColumn(name = "exam_id", foreignKey = @ForeignKey(name = "student_exam_exam_id_fk"))
    private Exam exam;

}
