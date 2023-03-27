package com.test.controller.student;

import com.test.entities.Exam;
import com.test.entities.Student;
import com.test.repositories.ExamRepository;
import com.test.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("student")
public class ChooseExamController {
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    @GetMapping("/choose-exam")
    public String getChooseExam(@RequestParam("studentId") int studentId, Model model, RedirectAttributes rAttrs) {
        Optional<Student> student = studentRepository.findById(studentId);
        List<Exam> exams = examRepository.findAll();

        if (exams.isEmpty() || student.isEmpty()) {
            rAttrs.addFlashAttribute("errorMessage", "Exam or student not found");
            return "redirect:/student/check";
        }

        model.addAttribute("exams", exams);
        return "student/choose-exam";
    }

    @PostMapping("/choose-exam")
    public String postChooseExam(@RequestParam("examId") int examId, @RequestParam("studentId") int studentId,
                                 RedirectAttributes rAttrs) {
        Optional<Exam> exam = examRepository.findById(examId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (exam.isEmpty() || student.isEmpty()) {
            rAttrs.addFlashAttribute("errorMessage", "Exam or student not found");
            return "redirect:/student/check";
        }

        rAttrs.addAttribute("studentId", studentId);
        rAttrs.addAttribute("examId", exam.get().getId());
        return "redirect:/student/exam";
    }
}
