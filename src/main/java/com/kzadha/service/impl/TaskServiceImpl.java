package com.kzadha.service.impl;

import com.kzadha.dao.TaskDAO;
import com.kzadha.dao.UserDAO;
import com.kzadha.dao.UserTaskDAO;
import com.kzadha.entity.TaskEntity;
import com.kzadha.entity.UserEntity;
import com.kzadha.entity.UserTaskEntity;
import com.kzadha.entity.UserTaskKey;
import com.kzadha.exception.ConflictException;
import com.kzadha.mapper.TaskMapper;
import com.kzadha.model.Task;
import com.kzadha.model.enums.TaskStatus;
import com.kzadha.model.enums.UserRole;
import com.kzadha.model.json.CreateTaskRequest;
import com.kzadha.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskDAO taskDAO;
    private final UserDAO userDAO;
    private final UserTaskDAO userTaskDAO;
    private final TaskMapper mapper;


    @Override
    public Task create(CreateTaskRequest request) {
        UserEntity initiator = userDAO.findById(request.getUserId()).orElseThrow(() -> new ConflictException("initiator not found"));
        TaskEntity taskEntity = TaskEntity.builder().user(initiator).description(request.getDescription()).status(TaskStatus.CREATED).build();
        taskEntity = taskDAO.save(taskEntity);
        //if there are signers
        final TaskEntity res = taskEntity;
        List<UserEntity> signers = userDAO.findByRoles(UserRole.ROLE_SIGNER);
        if (signers.size() > 0) {
            List<UserTaskEntity> userTasks = signers.stream().map(s-> UserTaskEntity.builder().task(res).user(s).signed(false).build()).collect(Collectors.toList());
            userTasks.forEach(userTaskDAO::save);
            move(res.getId(), TaskStatus.ON_HOLD);
        }


        return mapper.toModel(taskDAO.save(taskEntity));
    }

    @Override
    public Task setExecutor(Long userId, Long taskId) {
        UserEntity executor = userDAO.findById(userId).orElseThrow(() -> new ConflictException("executor not found"));
        TaskEntity taskEntity = taskDAO.findById(taskId).orElseThrow(() -> new ConflictException("task not found"));
        taskEntity.setExecutor(executor);
        return mapper.toModel(taskDAO.save(taskEntity));
    }

    @Override
    public void signTask(Long userId, Long taskId) {
        UserTaskEntity userTaskEntity = userTaskDAO.findById(UserTaskKey.builder().taskId(taskId).userId(userId).build()).orElseThrow(() -> new ConflictException("usertask not found"));
        userTaskEntity.setSigned(true);
        userTaskDAO.save(userTaskEntity);
        if(userTaskDAO.findByTaskAndSigned(taskId, false).size() == 0){
            userTaskEntity
        }
    }

    @Override
    public void move(Long taskId, TaskStatus status) {
        TaskEntity taskEntity = taskDAO.findById(taskId).orElseThrow(() -> new ConflictException("task not found"));
        if (status.equals(TaskStatus.CONFIRMED)){
            if(userTaskDAO.findByTaskAndSigned(taskId, false).size() > 0){
                throw new ConflictException("task is not signed by all signers yet");
            }
        } else if (status.equals(TaskStatus.CONFIRMED)){

        }


        taskEntity.setStatus(status);
        taskDAO.save(taskEntity);
    }
}
