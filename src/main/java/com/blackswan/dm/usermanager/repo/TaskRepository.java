package com.blackswan.dm.usermanager.repo;

import com.blackswan.dm.usermanager.model.Task;
import com.blackswan.dm.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskByUserIdAndId(Long userId, Long taskId);

    List<Task> findAllByUserId(Long userId);
}
