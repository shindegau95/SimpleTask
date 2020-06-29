package com.demo.SimpleTask.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name= "task", schema = "task_application")
public class Task implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="task_id")
    private long id;

    @Column(name="task_description")
    private String taskDescription;

    @Column(name = "is_done")
    private boolean done;


    public Task(long id, String taskDescription, boolean done) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.done = done;
    }

    public Task(String description, boolean done) {
        this.taskDescription = description;
        this.done = done;
    }
}
