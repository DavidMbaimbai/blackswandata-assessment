package com.blackswan.dm.usermanager.service.impl;

import com.blackswan.dm.usermanager.dto.UserDto;
import com.blackswan.dm.usermanager.exceptions.UserNotFoundException;
import com.blackswan.dm.usermanager.mapper.UserToUserDtoMapper;
import com.blackswan.dm.usermanager.model.User;
import com.blackswan.dm.usermanager.repo.UserRepository;
import com.blackswan.dm.usermanager.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserToUserDtoMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserToUserDtoMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User createUser(final UserDto userDto) {
        User user = userMapper.mapUserFromUserDto(userDto);
        final User savedUser = userRepository.saveAndFlush(user);
        return savedUser;
    }

    public User updateUser(final UserDto userDto, final Long userId) {
        User dbUser = userRepository.getById(userId);
        if (Objects.isNull(dbUser)) {
            throw new UserNotFoundException(String.format("User with ID: %d not found", userId));
        }
        dbUser.setFirstName(userDto.getFirstName());
        dbUser.setLastName(userDto.getLastName());
        dbUser.setUsername(userDto.getUsername());
        return userRepository.saveAndFlush(dbUser);
    }

    public UserDto findUserById(final Long userId) {
        User user = userRepository.getById(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("User with ID: %d not found", userId));
        }
        return userMapper.mapUserDtoFromUser(user);
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(userMapper.mapUserDtoFromUser(user));
        }
        return userDtos;
    }
}
