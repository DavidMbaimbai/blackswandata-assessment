package com.blackswan.dm.usermanager.rest;

import com.blackswan.dm.usermanager.dto.TaskDto;
import com.blackswan.dm.usermanager.dto.UserDto;
import com.blackswan.dm.usermanager.model.Task;
import com.blackswan.dm.usermanager.model.User;
import com.blackswan.dm.usermanager.service.TaskService;
import com.blackswan.dm.usermanager.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {UserController.class})
class UserControllerTest {
    @MockBean
    private UserService userService;
    @MockBean
    private TaskService taskService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private User user;
    private Task task;
    private ObjectMapper om;
    @MockBean
    private UserDto userDto;
    @MockBean
    private TaskDto taskDto;
    private String pattern = "yyyy-MM-dd HH:mm:ss";

    @BeforeEach
    void setUp() {

        user = new User("Davy", "David", "Mbaimbai");
        task = new Task("David","Testing", new Date());
        user.setId(1L);
        om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat(pattern));
        userDto = new UserDto("Davy", "David", "Mbaimbai");
        taskDto = new TaskDto("Davy", "Testing", new Date());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser() throws Exception {
        when(userService.createUser(any(UserDto.class))).thenReturn(user);
        this.mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(om.writeValueAsString(userDto)))
                .andExpect(status().isCreated());

    }

    @Test
    void findAllUsers() throws Exception {
        List<UserDto> userDtoList = userService.findAllUsers();
        when(userService.findAllUsers()).thenReturn(userDtoList);
        this.mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk());
    }

    @Test
    void saveTask() throws Exception {
        when(taskService.saveTask(taskDto, user.getId())).thenReturn(task);
        this.mockMvc.perform(post("/api/user/{user_id}/task",user.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(om.writeValueAsString(taskDto)))
                .andExpect(status().isCreated());
    }
}