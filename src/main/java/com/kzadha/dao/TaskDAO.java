package com.kzadha.dao;

import com.kzadha.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDAO extends CrudRepository<TaskEntity, Long> {
}