package com.demo.SimpleTask.repositories;

import com.demo.SimpleTask.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WHEN Dummy Repository is used")
class DummyTaskRepositoryTest {

    DummyTaskRepository repository;

    @BeforeEach
    void setUp() {
         repository = new DummyTaskRepository();
    }

    @Test
    @DisplayName("THEN by default findALL shall return 2 elements")
    void findAllDefault() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DisplayName("THEN after adding 2 records, the size shall be 4")
    void findAllAfterAdding() {
        repository.save(new Task(2, "abc", false));
        repository.save(new Task(1, "xyz", true));
        assertEquals(2, repository.findAll().size());

        repository.save(new Task(0, "abc", false));
        repository.save(new Task(0, "xyz", true));
        assertEquals(4, repository.findAll().size());
    }

    @Test
    @DisplayName("AND if initially the size is 2, THEN after save the size shall be 3")
    void save() {
        assertEquals(2, repository.findAll().size());
        repository.save(new Task(0, "abc", false));
        assertEquals(3, repository.findAll().size());
    }


    @Test
    void findById() {
        assertFalse(repository.findById(0l).isPresent());
        Task task = new Task(0, "abc", false);
        repository.save(task);
        assertFalse(repository.findById(0l).isPresent());

        Task task2 = new Task(3, "abc", false);
        repository.save(task2);
        assertEquals(task2, repository.findById(3l).get());
    }

    @Test
    void deleteById() {
        repository.deleteById(2l);
        assertAll(
                () -> assertEquals(1, repository.findAll().size()),
                () -> assertEquals(Optional.empty(), repository.findById(2l))
        );
    }
}