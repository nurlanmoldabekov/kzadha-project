package com.kzadha.service.impl;

import com.kzadha.dao.TaskRepository;
import com.kzadha.dao.UserRepository;
import com.kzadha.exception.ConflictException;
import com.kzadha.model.Task;
import com.kzadha.model.User;
import com.kzadha.model.UserTask;
import com.kzadha.model.enums.TaskStatus;
import com.kzadha.model.enums.UserRole;
import com.kzadha.security.UserPrincipal;
import com.kzadha.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task create(Task request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        final Long newId = taskRepository.getNewId();
        request.setStatus(TaskStatus.CREATED);
        request.setId(newId);
        request.setUserId(userPrincipal.getId());

        taskRepository.insert(request);

        //if there are signers
        List<User> signers = userRepository.findByRole(UserRole.ROLE_SIGNER);
        if (signers.size() > 0) {
            // Every signer should sign
            List<UserTask> userTasks = signers.stream().map(s-> UserTask.builder().taskId(newId).userId(s.getId()).build()).collect(Collectors.toList());

            userTasks.forEach(taskRepository::insertUserTask);
            move(newId, TaskStatus.ON_HOLD);
        }


        return taskRepository.findById(newId);
    }

    @Override
    public void setExecutor(Long userId, Long taskId) {
        validateUserId(userId);
        validateTaskId(taskId);
        taskRepository.setExecutor(userId, taskId);
    }

    @Override
    public void signTask(Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        validateTaskId(taskId);
        taskRepository.updateSigned(userPrincipal.getId(), taskId, true);
        if(taskRepository.findByTaskAndSigned(taskId, false) == 0){
            move(taskId, TaskStatus.CONFIRMED);
        }
    }

    @Override
    public void move(Long taskId, TaskStatus status) {
        validateTaskId(taskId);
        Task task = taskRepository.findById(taskId);
        if (status.equals(TaskStatus.CONFIRMED)){
            if(taskRepository.findByTaskAndSigned(taskId, false) > 0){
                throw new ConflictException("task is not signed by all signers yet");
            }
        } else if (status.equals(TaskStatus.IN_PROGRESS) && !task.getStatus().equals(TaskStatus.CONFIRMED)){
            throw new ConflictException("task should be confirmed before moving to progress");
        } else if (status.equals(TaskStatus.FINISHED) && !task.getStatus().equals(TaskStatus.IN_PROGRESS)){
            throw new ConflictException("task should be in progress before moving to finished");
        }


        task.setStatus(status);
        taskRepository.updateStatus(taskId, status);
    }

    private void validateTaskId(Long taskId){
        if (taskRepository.findById(taskId) == null) {
            throw new ConflictException("task not found");
        }
    }
    private void validateUserId(Long userId){
        if (userRepository.findById(userId) == null) {
            throw new ConflictException("user not found");
        }

    }
}
