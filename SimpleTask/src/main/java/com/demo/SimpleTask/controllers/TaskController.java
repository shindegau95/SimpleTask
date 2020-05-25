package com.demo.SimpleTask.controllers;

import com.demo.SimpleTask.model.Task;
import com.demo.SimpleTask.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    /*
    * idempotent -> 1 * c = c
    * GET * -> get something
    * POST -> make changes to repo
    * PUT * -> modify something
    * DELETE *  -> delete something
    * */


    @Autowired
    TaskService taskService;

    @GetMapping("getTasks")
    public ResponseEntity<List<Task>> getTasks(){
        List<Task> tasks  = taskService.getTasks();
        ResponseEntity<List<Task>> response = new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
        return response;
    }

    @PostMapping(value="addTask", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        Task resultTask = taskService.addTask(task.getTaskDescription(), task.isDone());
        return  new ResponseEntity<Task>(resultTask, HttpStatus.CREATED);
    }

    @PutMapping(value="updateTask", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> updatedTask(@RequestBody Task task){
        boolean success = taskService.updateCompletedStatus(task);
        if(success){
            return new ResponseEntity<>("SUCCESS", HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>("FAILURE", HttpStatus.EXPECTATION_FAILED);
        }
    }


    @DeleteMapping(value="deleteTask")
    public ResponseEntity<Task> deleteTask(@RequestBody Task task){
        taskService.deleteTasks(task.getTaskDescription());
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
