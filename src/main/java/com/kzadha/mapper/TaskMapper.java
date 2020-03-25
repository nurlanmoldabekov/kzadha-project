package com.kzadha.mapper;

import com.kzadha.entity.TaskEntity;
import com.kzadha.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskEntity toEntity(Task model);
    List<TaskEntity> toEntityList(List<Task> models);
    Task toModel(TaskEntity entity);
    List<Task> toModelList(List<TaskEntity> entities);
}