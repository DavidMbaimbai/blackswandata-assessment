package com.blackswan.dm.usermanager.rest;

import com.blackswan.dm.usermanager.dto.TaskDto;
import com.blackswan.dm.usermanager.dto.UserDto;
import com.blackswan.dm.usermanager.model.Task;
import com.blackswan.dm.usermanager.model.User;
import com.blackswan.dm.usermanager.service.TaskService;
import com.blackswan.dm.usermanager.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @PostMapping(value = "/api/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {

        userService.createUser(userDto);
    }

    @PutMapping("/api/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody UserDto userDto, @PathVariable("id") Long userId) {
        userService.updateUser(userDto, userId);
    }

    @GetMapping("/api/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable("id") Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/api/user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/api/user/{user_id}/task")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTask(@RequestBody TaskDto taskDto, @PathVariable("user_id") Long userId) {
        taskService.saveTask(taskDto, userId);
    }

    @PutMapping("/api/user/{user_id}/task/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@NotNull @RequestBody TaskDto taskDto,
                           @PathVariable("user_id") Long userId,
                           @PathVariable("task_id") Long taskId) throws Exception {
        taskService.updateTask(taskDto, userId, taskId);
    }

    @DeleteMapping("/api/user/{user_id}/task/{task_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("user_id") Long userId,
                           @PathVariable("task_id") Long taskId) throws Exception {
        taskService.deleteTask(userId, taskId);
    }

    @GetMapping("/api/user/{user_id}/task/{task_id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto findTaskById(@PathVariable("user_id") Long userId,
                             @PathVariable("task_id") Long taskId) {
        return taskService.findTaskById(userId, taskId);
    }

    @GetMapping("/api/user/{user_id}/task")
    public List<TaskDto> findAllTasks(@PathVariable("user_id") Long userId) {
        return taskService.findAllTasks(userId);
    }
}
