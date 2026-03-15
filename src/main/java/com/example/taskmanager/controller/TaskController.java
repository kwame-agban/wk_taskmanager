package com.example.taskmanager.controller;

import com.example.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String tasks(Model model, Principal principal) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("username", principal.getName());
        return "tasks";
    }

    @PostMapping
    public String addTask(@RequestParam String title) {
        taskService.addTask(title);
        return "redirect:/tasks";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
