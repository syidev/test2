package com.test.controller.teacher;

import com.test.entities.Equation;
import com.test.repositories.EquationRepository;
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
@RequestMapping("teacher/equation")
public class EquationController {
    private final EquationRepository equationRepository;

    @GetMapping("/list")
    public String getEquations(Model model) {
        List<Equation> equations = equationRepository.findAll();

        model.addAttribute("equations", equations);
        return "teacher/equation/equation-list";
    }

    @GetMapping("/add")
    public String addEquation(Model model) {
        model.addAttribute("equation", new Equation());
        return "teacher/equation/equation-add";
    }

    @PostMapping("/add")
    public String addEquation(@Valid @ModelAttribute Equation equation, BindingResult result) {
        if (result.hasErrors()) {
            return "teacher/equation/equation-add";
        }

        equationRepository.save(equation);

        return "redirect:/teacher/equation/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEquation(@PathVariable("id") int id, RedirectAttributes rAttrs) {
        try {
            equationRepository.deleteById(id);
        } catch (Exception e) {
            rAttrs.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/teacher/equation/list";
    }

    @GetMapping("/edit/{id}")
    public String editEquation(@PathVariable("id") int id, Model model, RedirectAttributes rAttrs) {
        Optional<Equation> equation = equationRepository.findById(id);

        if (equation.isEmpty()) {
            rAttrs.addFlashAttribute("errorMessage", "Equation with id: %s not found".formatted(id));
            return "redirect:/teacher/equation/list";
        }

        model.addAttribute("equation", equation);
        return "teacher/equation/equation-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEquation(@ModelAttribute Equation equation) {
        equationRepository.save(equation);

        return "redirect:/teacher/equation/list";
    }
}
