package com.blackswan.dm.usermanager.mapper;

import com.blackswan.dm.usermanager.dto.UserDto;
import com.blackswan.dm.usermanager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserToUserDtoMapper {
    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName")
    })
    User mapUserFromUserDto(UserDto userDto);

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "id", source = "id")
    })
    UserDto mapUserDtoFromUser(User user);

}
