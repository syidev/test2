package com.test.controller.student;

import com.test.entities.Student;
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

@Controller
@AllArgsConstructor
@RequestMapping("student")
public class CheckController {
    private final StudentRepository studentRepository;

    @GetMapping("/check")
    public String getCheck() {
        return "student/student-check";
    }

    @PostMapping("/check")
    public String postCheck(@RequestParam("name") String name, Model model, RedirectAttributes rAttrs) {
        List<Student> students = studentRepository.findByName(name);

        if (students.isEmpty()) {
            model.addAttribute("errorStudentMessage", "Student not found");
            return "student/student-check";
        }

        rAttrs.addAttribute("studentId", students.get(0).getId());
        return "redirect:/student/choose-exam";
    }
}
