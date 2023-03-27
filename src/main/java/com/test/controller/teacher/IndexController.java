package com.test.controller.teacher;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {
    @GetMapping("/teacher")
    public String getIndex() {
        return "teacher/index";
    }
}
