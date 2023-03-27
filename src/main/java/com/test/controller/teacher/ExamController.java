package com.test.controller.teacher;

import com.test.entities.Equation;
import com.test.entities.Exam;
import com.test.repositories.EquationRepository;
import com.test.repositories.ExamRepository;
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
@RequestMapping("teacher/exam")
public class ExamController {
    private final ExamRepository examRepository;
    private final EquationRepository equationRepository;

    @GetMapping("/list")
    public String getExams(Model model) {
        List<Exam> exams = examRepository.findAll();

        model.addAttribute("exams", exams);
        return "teacher/exam/exam-list";
    }

    @GetMapping("/add")
    public String addExam(Model model) {
        List<Equation> equations = equationRepository.findAll();

        model.addAttribute("equations", equations);
        model.addAttribute("exam", new Exam());
        return "teacher/exam/exam-add";
    }

    @PostMapping("/add")
    public String addExam(@Valid @ModelAttribute Exam exam, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Equation> equations = equationRepository.findAll();

            model.addAttribute("equations", equations);
            return "teacher/exam/exam-add";
        }

        examRepository.save(exam);

        return "redirect:/teacher/exam/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable("id") int id, RedirectAttributes rAttrs) {
        try {
            examRepository.deleteById(id);
        } catch (Exception e) {
            rAttrs.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/teacher/exam/list";
    }

    @GetMapping("/edit/{id}")
    public String editExam(@PathVariable("id") int id, Model model, RedirectAttributes rAttrs) {
        Optional<Exam> exam = examRepository.findById(id);
        List<Equation> equations = equationRepository.findAll();

        if (exam.isEmpty()) {
            rAttrs.addFlashAttribute("errorMessage", "Exam with id: %s not found".formatted(id));
            return "redirect:/teacher/exam/list";
        }

        model.addAttribute("exam", exam.get());
        model.addAttribute("equations", equations);
        return "teacher/exam/exam-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateExam(@ModelAttribute Exam exam) {
        examRepository.save(exam);

        return "redirect:/teacher/exam/list";
    }
}
