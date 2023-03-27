package com.test.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exam_answer")
public class ExamAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_exam_id")
    private StudentExam exam;

    @ManyToOne
    @JoinColumn(name = "equation_id", nullable = false)
    private Equation equation;

    @Column(name = "student_answer", nullable = false)
    private Double studentAnswer;
}
