package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String createTask(Model model){



        model.addAttribute("task", new TaskDTO());
        model.addAttribute("employees",userService.findEmployees());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/create";
    }

    @PostMapping("/create")
    public String insertTask(TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") Long id, Model model){
        taskService.deleteById(id);

        return "redirect:/task/create";

    }

    @GetMapping("/update/{taskId}")
    public String editProject(@PathVariable("taskId") Long taskId, Model model){

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("employees",userService.findEmployees());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/update";
    }

//    @PostMapping("/update/{taskId}")
//    public String updateTask(@PathVariable("taskId") Long taskId, TaskDTO task){
//
//        task.setId(taskId);
//
//        taskService.update(task);
//
//        return "redirect:/task/create";
//    }

    @PostMapping("/update/{id}") // same as above, if the path variable name and the field name of the object has the same name, spring can match them and do it automatically
    public String updateTask(TaskDTO task){

        taskService.update(task);

        return "redirect:/task/create";
    }

}
