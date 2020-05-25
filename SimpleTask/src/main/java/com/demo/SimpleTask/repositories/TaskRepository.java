package com.demo.SimpleTask.repositories;

import com.demo.SimpleTask.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository {
    List<Task> findAll();
    Task save(Task task);
    Long deleteByTaskDescription(String taskDescription);
    Optional<Task> findById(Long aLong);
}
