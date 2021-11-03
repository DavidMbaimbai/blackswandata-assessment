package com.blackswan.dm.usermanager.service;

import com.blackswan.dm.usermanager.dto.UserDto;
import com.blackswan.dm.usermanager.model.User;

import java.util.List;

public interface UserService {
    User createUser(final UserDto userDto);

    User updateUser(final UserDto userDto, final Long userId);

    UserDto findUserById(final Long userId);

    List<UserDto> findAllUsers();
}
