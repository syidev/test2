package com.test.repositories;

import com.test.entities.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentExamRepository extends JpaRepository<StudentExam, Integer> {
    List<StudentExam> findByStudentId(Integer studentId);
}
