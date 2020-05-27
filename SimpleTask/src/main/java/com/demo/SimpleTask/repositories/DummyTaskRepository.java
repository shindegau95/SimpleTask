package com.demo.SimpleTask.repositories;

import com.demo.SimpleTask.model.Task;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DummyTaskRepository implements TaskRepository{
    private List<Task> tasks;

    public DummyTaskRepository() {
        this.tasks = new ArrayList<>();
        this.seedData();
    }

    private void seedData(){
        Task t1 = new Task(1, "First Todo", true);
        Task t2 = new Task(2, "Second Todo", false);
        this.tasks.addAll(Arrays.asList(t1,t2));
    }


    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return this.tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public Task save(Task task) {
        //if task is present then update, else add and save

        if(Objects.isNull(task.getId()) || task.getId() == 0){
            long newId = tasks.stream().count() + 1;
            task.setId(newId);
            this.tasks.add(task);
        }else{
            this.tasks.stream()
                    .filter(t -> t.getId() == task.getId())
                    .map(t -> task);
        }
        return task;
    }

    @Override
    public void deleteById(Long id) {
        this.tasks = this.tasks.stream().filter(t -> t.getId() != id).collect(Collectors.toList());
    }


}
