package com.test.controller.student;

import com.test.entities.Exam;
import com.test.entities.Student;
import com.test.entities.StudentExam;
import com.test.repositories.ExamRepository;
import com.test.repositories.StudentExamRepository;
import com.test.repositories.StudentRepository;
import com.test.service.StudentExamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("student")
public class PassExamController {
    private final ExamRepository examRepository;
    private final StudentExamRepository studentExamRepository;
    private final StudentRepository studentRepository;
    private final StudentExamService studentExamService;

    @GetMapping("/exam")
    public String getExam(@RequestParam("examId") int examId, @RequestParam("studentId") int studentId, Model model,
                          RedirectAttributes rAttrs) {
        Optional<Exam> exam = examRepository.findById(examId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (exam.isEmpty() || student.isEmpty()) {
            rAttrs.addFlashAttribute("errorMessage", "Student or exam not found");
            return "redirect:/student/check";
        }

        model.addAttribute("student", student.get());
        model.addAttribute("exam", exam.get());
        return "student/exam";
    }

    @PostMapping("/exam")
    public String postExam(@RequestParam("studentId") int studentId, @RequestParam("examId") int examId,
                           @RequestBody MultiValueMap<String, String> formData, RedirectAttributes rAttrs) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Exam> exam = examRepository.findById(examId);

        if (student.isEmpty() || exam.isEmpty()) {
            rAttrs.addFlashAttribute("errorMessage", "Student or exam not found");
            return "redirect:/student/check";
        }

        StudentExam studentExam = studentExamService.createStudentExam(exam.get(), student.get(), formData);
        StudentExam savedStudentExam = studentExamRepository.save(studentExam);

        rAttrs.addAttribute("studentExamId", savedStudentExam.getId());
        return "redirect:/student/grade";
    }
}
