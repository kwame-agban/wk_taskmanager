package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class TaskService
{
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(String title) {
        Task task = new Task(counter.getAndIncrement(), title);
        tasks.add(task);
    }

    public void deleteTask(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
