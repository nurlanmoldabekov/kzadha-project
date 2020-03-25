package com.kzadha.dao;

import com.kzadha.entity.UserEntity;
import com.kzadha.model.enums.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    List<UserEntity> findAll();
    List<UserEntity> findByRoles(UserRole role);
}