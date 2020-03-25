package com.kzadha.mapper;

import com.kzadha.model.User;
import com.kzadha.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User model);
    List<UserEntity> toEntityList(List<User> models);
    User toModel(UserEntity entity);
    List<User> toModelList(List<UserEntity> entities);
}