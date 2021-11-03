package com.blackswan.dm.usermanager.rest;

import com.blackswan.dm.usermanager.service.TaskService;
import com.blackswan.dm.usermanager.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public UserService userService(){
        return Mockito.mock(UserService.class);
    }

    @Bean
    public TaskService taskService(){
        return Mockito.mock(TaskService.class);
    }
}
