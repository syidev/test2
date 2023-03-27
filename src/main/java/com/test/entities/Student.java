package com.test.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Student name cannot be blank")
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @OneToMany
    @JoinColumn(name = "exam_id")
    private List<StudentExam> exams;
}
