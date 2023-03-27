package com.test.controller.student;

import com.test.entities.StudentExam;
import com.test.repositories.StudentExamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("student")
public class GradeController {
    private final StudentExamRepository studentExamRepository;

    @GetMapping("/grade{studentExamId}")
    public String getGrade(@RequestParam("studentExamId") int studentExamId, Model model, RedirectAttributes rAttr) {
        Optional<StudentExam> studentExam = studentExamRepository.findById(studentExamId);

        if (studentExam.isEmpty()) {
            rAttr.addFlashAttribute("errorMessage",
                    "Student exam with id: %s does not exist".formatted(studentExamId));
            return "redirect:/student/check";
        }

        model.addAttribute("studentExam", studentExam.get());
        return "student/grade";
    }
}
