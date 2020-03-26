package com.kzadha.dao;

import com.kzadha.mapper.TaskMapper;
import com.kzadha.model.Task;
import com.kzadha.model.User;
import com.kzadha.model.UserTask;
import com.kzadha.model.enums.TaskStatus;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class TaskRepository {
    private final JdbcTemplate template;
    private static final String GET_ALL = "SELECT * FROM t_tasks;";
    private static final String GET_NEW_TASK_ID = "SELECT nextval('hibernate_sequence')";
    private static final String GET_BY_ID = "SELECT * FROM t_tasks WHERE id=?";
    private static final String GET_BY_ID_AND_SIGNED = "SELECT count(*) FROM t_userstotasks WHERE ntaskid=? AND issigned=?";
    private static final String INSERT = "INSERT INTO t_tasks(id, vdescription, vstatus, nuserid, nexecutoruserid) VALUES (?,?,?,?,?)";
    private static final String INSERT_USER_TASK = "INSERT INTO t_userstotasks(nuserid, ntaskid, issigned) VALUES (?,?,?)";
    private static final String SET_EXECUTOR = "UPDATE t_tasks SET nexecutoruserid=? WHERE id=?";
    private static final String UPDATE_SIGNED = "UPDATE t_userstotasks SET issigned=? WHERE nuserid=? AND ntaskid=?";
    private static final String UPDATE_STATUS = "UPDATE t_tasks SET vstatus=? WHERE id=?";

    public Long getNewId(){
        return template.queryForObject(GET_NEW_TASK_ID, Long.class);
    }

    public Task findById(Long id){
        return (Task) template.queryForObject(GET_BY_ID, new TaskMapper(), id);
    }

    public List<Task> findAll(){
        return template.query(GET_ALL, new TaskMapper());
    }

    public Integer findByTaskAndSigned(Long taskId, boolean isSigned){
        return template.queryForObject(GET_BY_ID_AND_SIGNED, Integer.class, taskId, isSigned);
    }
    public void insert(Task input){
        template.update(INSERT, input.getId(), input.getDescription(), input.getStatus().name(), input.getUserId(), input.getExecutorUserId());
    }

    public void insertUserTask(UserTask input){
        template.update(INSERT_USER_TASK, input.getUserId(), input.getTaskId(), input.isSigned());
    }

    public void setExecutor(Long userId, Long taskId){
        template.update(SET_EXECUTOR, userId, taskId);
    }

    public void updateSigned(Long userId, Long taskId, boolean isSigned){
        template.update(UPDATE_SIGNED, isSigned, userId, taskId);
    }

    public void updateStatus(Long taskId, TaskStatus status){
        template.update(UPDATE_STATUS, status.name(), taskId);
    }
}
