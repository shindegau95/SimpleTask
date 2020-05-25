package com.demo.SimpleTask.repositories;

import com.demo.SimpleTask.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface JPATaskRepository extends JpaRepository<Task, Long>, TaskRepository {
    Long deleteByTaskDescription(String taskDescription);
}
