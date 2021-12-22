package org.matusikl.controller;

import org.matusikl.model.Task;
import org.matusikl.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping(path = "/get/task/{id}", produces = "application/json")
    public ResponseEntity<Task> getTask(@PathVariable ("id") Integer id){
        Task task = taskService.getTask(id);
        return ResponseEntity
                .ok()
                .body(task);
    }

    @GetMapping(path = "/get/tasks", produces = "application/json")
    public ResponseEntity<List<Task>> getTasks(){
        List<Task> taskList = taskService.getTasks();
        return ResponseEntity
                .ok()
                .body(taskList);
    }

    @PostMapping(path = "/add/task", produces = "application/json")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        Task addedTask = taskService.addTask(task);
        return ResponseEntity
                .ok()
                .body(addedTask);
    }

    @DeleteMapping(path = "/delete/task/{id}", produces = {"application/json", "text/plain"})
    public ResponseEntity<String> deleteTask(@PathVariable ("id") Integer id){
        taskService.deleteTask(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Task with id: " + id + " deleted");
    }

    @PutMapping(value = "/update/task/{id}", produces = "application/json")
    public ResponseEntity<Task> updateTask(@PathVariable ("id") Integer id, @RequestBody Task task){
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity
                .ok()
                .body(updatedTask);
    }
}
