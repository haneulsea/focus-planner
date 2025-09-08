package io.github.haneulsea.focusplanner.task;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskRequest dto);

    TaskResponse toResponse(Task entity);

}
