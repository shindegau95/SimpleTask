package com.demo.SimpleTask.repositories;

import com.demo.SimpleTask.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBCTaskRepository implements TaskRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Task> findAll() {
        String selectQuery = "Select * from TASK_APPLICATION.task";
        return jdbcTemplate.query(selectQuery
                , (resultSet, i) -> {
                    return new Task(resultSet.getLong("task_id"),
                            resultSet.getString("task_description"),
                            resultSet.getBoolean("is_done"));
                });
    }

    @Override
    public Task save(Task task) {
        String insertQuery = "insert into TASK_APPLICATION.TASK( task_description, is_done) values(?, ?);";
        jdbcTemplate.update(insertQuery, task);
        return task;
    }

    @Override
    public Long deleteByTaskDescription(String taskDescription) {
        return null;
    }

    @Override
    public Optional<Task> findById(Long aLong) {
        return Optional.empty();
    }

}
