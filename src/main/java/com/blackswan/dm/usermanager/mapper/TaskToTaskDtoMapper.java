package com.blackswan.dm.usermanager.mapper;

import com.blackswan.dm.usermanager.dto.TaskDto;
import com.blackswan.dm.usermanager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TaskToTaskDtoMapper {
    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "dateTime", source = "dateTime")
    })
    Task mapTaskFromTaskDto(TaskDto taskDto);
    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "dateTime", source = "dateTime"),
            @Mapping(target = "id", source = "id")
    })
    TaskDto mapTaskDtoFromTask(Task task);
}
