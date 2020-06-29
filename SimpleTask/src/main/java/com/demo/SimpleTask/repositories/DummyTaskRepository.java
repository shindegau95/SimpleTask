package com.demo.SimpleTask.repositories;

import com.demo.SimpleTask.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class DummyTaskRepository implements TaskRepository{
    private List<Task> tasks;

    public DummyTaskRepository() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> findAll(){
        return tasks;
    }

    @Override
    public Task save(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public Long deleteByTaskDescription(String taskDescription) {
        Optional<Task> foundtask = tasks.stream().filter(task -> taskDescription.equals(task.getTaskDescription())).findFirst();
        if(foundtask.isPresent()){
            Long id = foundtask.get().getId();
            tasks.remove(foundtask.get());
            return id;
        }else{
            return -1l;
        }
    }

    @Override
    public Optional<Task> findById(Long id) {
        return tasks.stream().filter(task -> id.equals(task.getId())).findFirst();
    }


}
