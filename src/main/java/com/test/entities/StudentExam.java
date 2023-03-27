package com.test.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student_exam")
public class StudentExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "student_exam_id")
    private List<ExamAnswer> examAnswers;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public Integer getGrade() {
        int grade = 0;
        for (ExamAnswer eA : examAnswers) {
            if (eA.getEquation().getAnswer().equals(eA.getStudentAnswer())) {
                grade++;
            }
        }

        return grade;
    }
}
