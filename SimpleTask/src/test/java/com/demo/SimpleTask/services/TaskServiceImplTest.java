package com.demo.SimpleTask.services;

import com.demo.SimpleTask.model.Task;
import com.demo.SimpleTask.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskServiceImplUnderTest;

    @Mock
    TaskRepository dummyTaskRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetTasks() {
        // Setup
        final List<Task> expectedResult = Arrays.asList(new Task(0L, "taskDescription", false));

        // Configure TaskRepository.findAll(...).
        final List<Task> tasks = Arrays.asList(new Task(0L, "taskDescription", false));
        when(dummyTaskRepository.findAll()).thenReturn(tasks);

        // Run the test
        final List<Task> result = taskServiceImplUnderTest.getTasks();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAddTask() {
        // Setup
        final Task expectedResult = new Task(0L, "taskDescription", false);
        when(dummyTaskRepository.save(new Task(0L, "taskDescription", false))).thenReturn(new Task(0L, "taskDescription", false));

        // Run the test
        final Task result = taskServiceImplUnderTest.addTask("taskDescription", false);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateCompletedStatus() {
        // Setup
        final Task task = new Task(0L, "taskDescription", false);

        // Configure TaskRepository.findById(...).
        final Optional<Task> task1 = Optional.of(new Task(0L, "taskDescription", false));
        when(dummyTaskRepository.findById(0L)).thenReturn(task1);

        when(dummyTaskRepository.save(new Task(0L, "taskDescription", false))).thenReturn(new Task(0L, "taskDescription", false));

        // Run the test
        final boolean result = taskServiceImplUnderTest.updateCompletedStatus(task);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testDeleteTask() {
        // Setup
        final Task task = new Task(0L, "taskDescription", false);

        // Run the test
        final boolean result = taskServiceImplUnderTest.deleteTask(task);

        // Verify the results
        assertThat(result).isTrue();
        verify(dummyTaskRepository).deleteById(0L);
    }
}
