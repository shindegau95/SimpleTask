package com.demo.SimpleTask.repositories;

import com.demo.SimpleTask.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyTaskRepositoryTest {
    DummyTaskRepository repository;
    @BeforeEach
    void setUp() {
         repository = new DummyTaskRepository();
    }

    @Test
    void findAllDefault() {
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void findAllAfterAdding() {
        repository.save(new Task(0, "abc", false));
        repository.save(new Task(1, "xyz", true));
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void save() {
        assertEquals(0, repository.findAll().size());
        repository.save(new Task(0, "abc", false));
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void deleteByTaskDescription() {
        assertEquals(-1,repository.deleteByTaskDescription("abc"));
        assertEquals(-1,repository.deleteByTaskDescription(""));

        repository.save(new Task(0, "", false));
        assertEquals(0,repository.deleteByTaskDescription(""));
        assertEquals(-1,repository.deleteByTaskDescription(""));

        Task task = new Task(1, "abc",false);
        repository.save(task);
        assertEquals(-1,repository.deleteByTaskDescription(""));
        assertEquals(1,repository.deleteByTaskDescription("abc"));
        assertEquals(-1,repository.deleteByTaskDescription("abc"));

    }

    @Test
    void findById() {
        assertFalse(repository.findById(0l).isPresent());

        Task task = new Task(0l, "abc", false);
        repository.save(task);
        assertTrue(repository.findById(0l).isPresent());
        assertFalse(repository.findById(1l).isPresent());
    }
}