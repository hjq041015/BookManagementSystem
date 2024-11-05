package com.google.Controller;

import com.google.server.UserServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    @Resource
    UserServer userServer;
    @GetMapping("/student")
    public String student(Model model) {
        model.addAttribute("student_list",userServer.getStudentList());
        return "student";
    }
}
