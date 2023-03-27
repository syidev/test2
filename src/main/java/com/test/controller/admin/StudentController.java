package com.test.controller.admin;

import com.test.entities.Student;
import com.test.repositories.StudentRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("admin/student")
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping("/list")
    public String getStudents(Model model) {
        List<Student> students = studentRepository.findAll();

        model.addAttribute("students", students);
        return "admin/student-list";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "admin/student-add";
    }

    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/student-add";
        }

        studentRepository.save(student);

        return "redirect:/admin/student/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, RedirectAttributes rAttrs) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            rAttrs.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/student/list";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") int id, Model model, RedirectAttributes rAttrs) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            rAttrs.addFlashAttribute("errorMessage", "Student with id: %s not found".formatted(id));
            return "redirect:/admin/student/list";
        }

        model.addAttribute("student", student.get());
        return "admin/student-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@ModelAttribute Student student) {
        studentRepository.save(student);

        return "redirect:/admin/student/list";
    }
}
