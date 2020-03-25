package com.kzadha.dao;

import com.kzadha.entity.UserTaskEntity;
import com.kzadha.entity.UserTaskKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskDAO extends CrudRepository<UserTaskEntity, UserTaskKey> {
    List<UserTaskDAO> findByTaskAndSigned(Long taskId, boolean signed);
}