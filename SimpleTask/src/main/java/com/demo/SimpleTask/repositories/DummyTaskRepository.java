package com.demo.SimpleTask.repositories;

import com.demo.SimpleTask.model.Task;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class DummyTaskRepository implements TaskRepository{
    public List<Task> findAll(){
        Task t1 = new Task(1, "First Todo", true);
        Task t2 = new Task(2, "Second Todo", false);
        return Arrays.asList(t1, t2);
    }

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public Optional<Task> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }


}
