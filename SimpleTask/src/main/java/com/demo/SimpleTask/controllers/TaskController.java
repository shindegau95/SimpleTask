package com.demo.SimpleTask.controllers;

import com.demo.SimpleTask.model.CommonResponse;
import com.demo.SimpleTask.model.Task;
import com.demo.SimpleTask.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseBody
    public CommonResponse<List<Task>> getTasks(){
        List<Task> tasks  = taskService.getTasks();
        CommonResponse<List<Task>> response = new CommonResponse<List<Task>>(tasks, HttpStatus.OK);
        return response;
    }

    @PostMapping(value="addTask", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public CommonResponse<Task> addTask(@RequestBody Task task){
        Task resultTask = taskService.addTask(task.getTaskDescription(), task.isDone());
        return  new  CommonResponse<Task>(resultTask, HttpStatus.CREATED);
    }

    @PutMapping(value="updateTask", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public CommonResponse<String> updatedTask(@RequestBody Task task){
        boolean success = taskService.updateCompletedStatus(task);
        if(success){
            return new CommonResponse<String>("SUCCESS", HttpStatus.OK);
        }else {
            return new  CommonResponse<String>("FAILURE", HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping(value="deleteTask")
    @ResponseBody
    public CommonResponse<String> deleteTask(@RequestBody Task task){
        if(taskService.deleteTask(task)){
            return new CommonResponse<String>("SUCCESS", HttpStatus.OK);
        }else{
            return new CommonResponse<String>("FAILURE", HttpStatus.CONFLICT);
        }
    }
}
