package io.github.haneulsea.focusplanner.task;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskRequest dto);

    TaskResponse toResponse(Task entity);

    public void updateEntity(TaskRequest dto, @MappingTarget Task entity);

}
