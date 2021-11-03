package com.blackswan.dm.usermanager.service.impl;

import com.blackswan.dm.usermanager.dto.UserDto;
import com.blackswan.dm.usermanager.mapper.UserToUserDtoMapper;
import com.blackswan.dm.usermanager.mapper.UserToUserDtoMapperImpl;
import com.blackswan.dm.usermanager.model.User;
import com.blackswan.dm.usermanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import({UserServiceImpl.class, UserToUserDtoMapperImpl.class})
@DataJpaTest
@Rollback
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto("Davy", "David", "Mbaimbai");
    }

    @Test
    void createUser() {
        User user = userService.createUser(userDto);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals("Davy", user.getUsername());
        assertEquals("David", user.getFirstName());
        assertEquals("Mbaimbai", user.getLastName());
    }

    @Test
    void updateUser() {
        User user = userService.createUser(userDto);
        UserDto updateDetails = new UserDto("Pauline", "Pauline", "Mbaimbai");
        User updatedUser = userService.updateUser(updateDetails, user.getId());
        assertNotNull(updatedUser);
        assertNotNull(updatedUser.getId());
        assertEquals("Pauline", user.getUsername());
        assertEquals("Pauline", user.getFirstName());
        assertEquals("Mbaimbai", user.getLastName());
    }

    @Test
    @Rollback
    void findUserById() {
        User user = userService.createUser(userDto);
        UserDto userDetails = userService.findUserById(user.getId());
        assertNotNull(userDetails);
        assertNotNull(userDetails.getId());
        assertEquals("Davy", userDetails.getUsername());
        assertEquals("David", userDetails.getFirstName());
        assertEquals("Mbaimbai", userDetails.getLastName());
    }

    @Test
    void findAllUsers() {
        userService.createUser(userDto);
        List<UserDto> userDtoList = userService.findAllUsers();
        assertNotNull(userDtoList);
        assertEquals(1, userDtoList.size());
        assertEquals("Davy", userDtoList.get(0).getUsername());
        assertEquals("David", userDtoList.get(0).getFirstName());
        assertEquals("Mbaimbai", userDtoList.get(0).getLastName());




    }
}