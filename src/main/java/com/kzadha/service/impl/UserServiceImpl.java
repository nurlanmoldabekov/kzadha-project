package com.kzadha.service.impl;

import com.kzadha.dao.UserDAO;
import com.kzadha.entity.UserEntity;
import com.kzadha.mapper.UserMapper;
import com.kzadha.model.User;
import com.kzadha.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final UserMapper userMapper;

    @Override
    public List<User> getAll() {
        List<UserEntity> users = userDAO.findAll();
        users.forEach(s-> s.setPassword(""));
        return userMapper.toModelList(users);
    }
}
