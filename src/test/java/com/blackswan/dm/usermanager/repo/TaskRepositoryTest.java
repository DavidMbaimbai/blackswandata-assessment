package com.blackswan.dm.usermanager.repo;

import com.blackswan.dm.usermanager.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    @Rollback
    void testFindTaskByUserIdAndId() {
        Task task = new Task("Testing", "Assignment Testing", new Date());
        task.setUserId(1L);
        Task savedTask = taskRepository.saveAndFlush(task);
        Task retrievedTask = taskRepository.findTaskByUserIdAndId(1L, savedTask.getId());
        assertNotNull(retrievedTask);
        assertNotNull(retrievedTask.getId());
        assertEquals("Testing", retrievedTask.getName());
        assertEquals("Assignment Testing", retrievedTask.getDescription());
    }

    @Test
    @Rollback
    void testFindAllByUserId(){
        Task task = new Task("Testing", "Assignment Testing", new Date());
        task.setUserId(1L);
        taskRepository.saveAndFlush(task);
        List<Task> retrievedTasks = taskRepository.findAllByUserId(1L);
        assertNotNull(retrievedTasks);
        assertEquals(1, retrievedTasks.size());
    }

    @Test
    @Rollback
    void testSavingTask() {
        Task task = new Task("Testing", "Assignment Testing", new Date());
        task.setUserId(1L);
        Task savedTask = taskRepository.saveAndFlush(task);
        assertNotNull(savedTask);
        assertNotNull(savedTask.getId());
        assertEquals("Testing", savedTask.getName());
        assertEquals("Assignment Testing", savedTask.getDescription());
    }
}
