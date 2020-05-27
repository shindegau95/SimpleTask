package com.demo.SimpleTask.services;

import com.demo.SimpleTask.model.Task;
import com.demo.SimpleTask.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{


    @Autowired
    TaskRepository dummyTaskRepository;

    @Override
    public List<Task> getTasks(){
        return dummyTaskRepository.findAll();
    }

    @Override
    public Task addTask(String description, boolean done) {
        Task task = new Task(description, done);
        return dummyTaskRepository.save(task);
    }

    @Override
    public boolean updateCompletedStatus(Task task) {
        boolean newStatus = task.isDone();
        Optional<Task> foundTask = dummyTaskRepository.findById(task.getId());
        if(foundTask.isPresent()){
            foundTask.get().setDone(newStatus);
            dummyTaskRepository.save(foundTask.get());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteTask(Task task) {
        try{
            dummyTaskRepository.deleteById(task.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
