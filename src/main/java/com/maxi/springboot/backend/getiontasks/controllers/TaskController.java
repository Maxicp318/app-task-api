package com.maxi.springboot.backend.getiontasks.controllers;

import com.maxi.springboot.backend.getiontasks.entities.Task;
import com.maxi.springboot.backend.getiontasks.services.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin({"https://gestion-tareas-frontend.netlify.app/", "http://localhost:4200"})
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Optional<Task> taskOp = taskService.getTaskById(id);

        if(taskOp.isPresent()){
            return ResponseEntity.ok(taskOp.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task){
        Optional<Task> taskOp = taskService.getTaskById(id);

        if(taskOp.isPresent()){
            Task taskDB = taskOp.orElseThrow();
            taskDB.setName(task.getName());
            taskDB.setExpirationDate(task.getExpirationDate());
            taskDB.setStatus(task.getStatus());

            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(taskDB));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id){
        Optional<Task> taskOp = taskService.deleteTaskById(id);

        if(taskOp.isPresent()){
            Task taskDeleted = taskOp.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(taskDeleted);
        }

        return ResponseEntity.notFound().build();
    }
}
