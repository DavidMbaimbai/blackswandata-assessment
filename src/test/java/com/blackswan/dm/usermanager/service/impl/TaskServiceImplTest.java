package com.blackswan.dm.usermanager.service.impl;

import com.blackswan.dm.usermanager.dto.TaskDto;
import com.blackswan.dm.usermanager.dto.UserDto;
import com.blackswan.dm.usermanager.mapper.TaskToTaskDtoMapperImpl;
import com.blackswan.dm.usermanager.mapper.UserToUserDtoMapperImpl;
import com.blackswan.dm.usermanager.model.Task;
import com.blackswan.dm.usermanager.model.User;
import com.blackswan.dm.usermanager.service.TaskService;
import com.blackswan.dm.usermanager.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import({TaskServiceImpl.class, TaskToTaskDtoMapperImpl.class,
        UserServiceImpl.class, UserToUserDtoMapperImpl.class})
@DataJpaTest
@Rollback
class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    private TaskDto taskDto;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        taskDto = new TaskDto("David", "Testing", new Date() );
        userDto = new UserDto("David", "David", "Mbaimbai");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveTask() {
        Task task = taskService.saveTask(taskDto, 1L);
        assertNotNull(task);
        assertNotNull(task.getId());
        assertEquals("David", task.getName());
        assertEquals("Testing", task.getDescription());
    }

    @Test
    void findAllTasks() {
        taskService.saveTask(taskDto,1L);
        List<TaskDto> taskDtoList = taskService.findAllTasks(1L);
        assertNotNull(taskDtoList);
        assertEquals(1, taskDtoList.size());
        assertEquals("David", taskDtoList.get(0).getName());
        assertEquals("Testing", taskDtoList.get(0).getDescription());
    }

    @Test
    void findTaskById() {
        User user = userService.createUser(userDto);
        Task task = taskService.saveTask(taskDto, user.getId());
        TaskDto taskDetails = taskService.findTaskById(user.getId(), task.getId());
        assertNotNull(taskDetails);
        assertNotNull(taskDetails.getId());
        assertEquals("David", taskDetails.getName());
        assertEquals("Testing", taskDetails.getDescription());

    }

    @Test
    void deleteTask() throws Exception {
        User user = userService.createUser(userDto);
        Task task = taskService.saveTask(taskDto, user.getId());
        Task taskDel = taskService.deleteTask(user.getId(), task.getId());
        assertNotNull(taskDel);
        assertNotNull(taskDel.getId());
        assertEquals("David", task.getName());
        assertEquals("Testing", task.getDescription());
        TaskDto nullTask = taskService.findTaskById(user.getId(), task.getId());
        assertNull(nullTask);

    }

    @Test
    void updateTask() throws Exception {
        User user = userService.createUser(userDto);
        Task task = taskService.saveTask(taskDto, user.getId());
        TaskDto updateDetails = new TaskDto("Pauline", "Testing", new Date());
        Task updatedTask = taskService.updateTask(updateDetails, task.getUserId(), task.getId());
        assertNotNull(updatedTask);
        assertNotNull(updatedTask.getId());
        assertEquals("Pauline", task.getName());
        assertEquals("Testing", task.getDescription());
    }
}