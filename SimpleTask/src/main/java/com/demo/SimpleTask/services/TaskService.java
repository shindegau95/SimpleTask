package com.demo.SimpleTask.services;

import com.demo.SimpleTask.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    //get Tasks
    List<Task> getTasks();

    //add Tasks
    Task addTask(String description, boolean done);

    //update Tasks
    boolean updateCompletedStatus(Task task);

    //delete Tasks
    boolean deleteTask(Task task);
}
