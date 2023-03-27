package com.test.service;

import com.test.entities.Exam;
import com.test.entities.ExamAnswer;
import com.test.entities.Student;
import com.test.entities.StudentExam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class StudentExamService {
    HelperService helperService;

    public StudentExam createStudentExam(Exam exam, Student student, MultiValueMap<String, String> formData) {
        List<ExamAnswer> examAnswerSet = helperService.convertExamFormToStudentExamAnswers(
            exam.getEquations(),
            formData.toSingleValueMap()
        );

        StudentExam studentExam = new StudentExam();
        studentExam.setExam(exam);
        studentExam.setStudent(student);
        studentExam.setDateTime(LocalDateTime.now());
        studentExam.setExamAnswers(examAnswerSet);

        return studentExam;
    }
}
