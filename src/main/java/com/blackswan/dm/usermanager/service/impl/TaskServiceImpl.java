package com.blackswan.dm.usermanager.service.impl;

import com.blackswan.dm.usermanager.dto.TaskDto;
import com.blackswan.dm.usermanager.exceptions.TaskNotFoundException;
import com.blackswan.dm.usermanager.exceptions.UserNotFoundException;
import com.blackswan.dm.usermanager.mapper.TaskToTaskDtoMapper;
import com.blackswan.dm.usermanager.model.Task;
import com.blackswan.dm.usermanager.model.User;
import com.blackswan.dm.usermanager.repo.TaskRepository;
import com.blackswan.dm.usermanager.repo.UserRepository;
import com.blackswan.dm.usermanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskToTaskDtoMapper taskMapper;

    public TaskServiceImpl(UserRepository userRepository,
                           TaskRepository taskRepository,
                           TaskToTaskDtoMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    public Task saveTask(TaskDto taskDto, Long userId) {
        Task task = taskMapper.mapTaskFromTaskDto(taskDto);
        User user = userRepository.getById(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("User with ID: %d not found", userId));
        }
        task.setUserId(userId);
        Task savedTask = taskRepository.saveAndFlush(task);
        return savedTask;
    }

    @Override
    public List<TaskDto> findAllTasks(Long userId) {
        User user = userRepository.getById(userId);
        if (Objects.isNull(user)) {
            return new ArrayList<>();
        }
        List<Task> allTasks = taskRepository.findAllByUserId(user.getId());
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : allTasks) {
            taskDtos.add(taskMapper.mapTaskDtoFromTask(task));
        }
        return taskDtos;
    }

    @Override
    public TaskDto findTaskById(Long userId, Long taskId) {
        User user = userRepository.getById(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("User with ID: %d not found", userId));
        }
        Task dbTask = taskRepository.findTaskByUserIdAndId(userId, taskId);
        return taskMapper.mapTaskDtoFromTask(dbTask);
    }

    @Override
    public Task deleteTask(Long userId, Long taskId) {
        User user = userRepository.getById(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("User with ID: %d not found", userId));
        }
        Task task = taskRepository.findTaskByUserIdAndId(user.getId(), taskId);
        taskRepository.delete(task);
        return task;
    }

    @Override
    public Task updateTask(TaskDto taskDto, Long userId, Long taskId) throws Exception {
        User user = userRepository.getById(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("User with ID: %d not found", userId));
        }
        Task dbTask = taskRepository.findTaskByUserIdAndId(user.getId(), taskId);
        if (Objects.isNull(dbTask)) {
            throw new TaskNotFoundException(String.format("Task for user with ID: %d and task number %d not found", userId, taskId));
        }
        dbTask.setName(taskDto.getName());
        return taskRepository.saveAndFlush(dbTask);
    }
}