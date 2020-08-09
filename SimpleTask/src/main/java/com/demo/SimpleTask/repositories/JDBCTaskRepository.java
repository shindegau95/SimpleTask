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
    public Optional<Task> findById(Long id) {
        String selectQuery = "Select * from TASK_APPLICATION.task where task_id = ?";
        try{
            Task task = jdbcTemplate.queryForObject(selectQuery,
                    new Object[]{id}
                    , (resultSet, i) -> {
                        return new Task(resultSet.getLong("task_id"),
                                resultSet.getString("task_description"),
                                resultSet.getBoolean("is_done"));
                    });
            return Optional.of(task);
        }catch (Exception e){
            return Optional.empty();
        }


    }

    @Override
    public Task save(Task task) {
        //if task is present then update, else add and save
        if(findById(task.getId()).isPresent()){
            String updateQuery = "update TASK_APPLICATION.TASK set task_description=?, is_done = ? where task_id = ?;";
            jdbcTemplate.update(updateQuery, task.getTaskDescription(), task.isDone(), task.getId());
            return task;
        }else{
            String insertQuery = "insert into TASK_APPLICATION.TASK( task_description, is_done) values(?, ?);";
            jdbcTemplate.update(insertQuery, task.getTaskDescription(), task.isDone());
            return task;
        }


    }

    @Override
    public void deleteById(Long id) {
        String deleteQuery = "DELETE from TASK_APPLICATION.task where task_id = ?";
        jdbcTemplate.update(deleteQuery,
                new Object[]{id});
    }

}
