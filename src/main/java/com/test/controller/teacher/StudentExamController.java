package com.test.controller.teacher;

import com.test.entities.Student;
import com.test.entities.StudentExam;
import com.test.repositories.StudentExamRepository;
import com.test.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/teacher/student-exam")
public class StudentExamController {
    private final StudentExamRepository studentExamRepository;
    private final StudentRepository studentRepository;

    @GetMapping("/list")
    public String getExams(Model model) {
        List<Student> students = studentRepository.findAll();

        model.addAttribute("students", students);
        return "teacher/students-exams/exams-by-students";
    }

    @GetMapping("/{studentId}")
    public String getStudentExams(@PathVariable("studentId") int studentId, Model model) {
        List<StudentExam> studentExams = studentExamRepository.findByStudentId(studentId);

        model.addAttribute("studentExams", studentExams);
        return "teacher/students-exams/student-exams";
    }

    @GetMapping("/search{studentName}")
    public String searchStudentExams(@RequestParam("studentName") String studentName, Model model) {
        List<Student> students;

        if (studentName.isEmpty()) {
            students = studentRepository.findAll();
        } else {
            students = studentRepository.findByNameContaining(studentName);
        }

        model.addAttribute("students", students);
        model.addAttribute("query", studentName);
        return "teacher/students-exams/exams-by-students";
    }
}
