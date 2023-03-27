package com.test.service;

import com.test.entities.Equation;
import com.test.entities.ExamAnswer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class HelperService {
    public List<ExamAnswer> convertExamFormToStudentExamAnswers(List<Equation> equationList, Map<String, String> request) {
        Map<Integer, Double> studentAnswers = request.entrySet().stream()
            .filter(e -> e.getKey().contains("equation_id"))
            .collect(Collectors.toMap(
                e -> Integer.valueOf(e.getKey().replaceAll("[^0-9]", "")),
                e -> Double.valueOf(e.getValue())
            ));

        List<ExamAnswer> examAnswers = new ArrayList<>();

        for (Equation equation : equationList) {
            ExamAnswer examAnswer = new ExamAnswer();
            examAnswer.setEquation(equation);
            examAnswers.add(examAnswer);
            examAnswer.setStudentAnswer(studentAnswers.get(examAnswer.getEquation().getId()));
        }

        return examAnswers;
    }
}
