package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;

    public ProjectController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model){


        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("users",userService.findAll());


        return "project/create";
    }
}
