package com.blackswan.dm.usermanager.service;

import com.blackswan.dm.usermanager.dto.TaskDto;
import com.blackswan.dm.usermanager.model.Task;

import java.util.List;

public interface TaskService {
    Task saveTask(TaskDto taskDto, Long userId);

    List<TaskDto> findAllTasks(Long userId);

    TaskDto findTaskById(Long userId, Long taskId);

    Task deleteTask(Long userId, Long taskId) throws Exception;

    Task updateTask(TaskDto taskDto, Long userId, Long taskId) throws Exception;
}
