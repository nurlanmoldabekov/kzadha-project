package com.kzadha.service.impl;

import com.kzadha.dao.UserRepository;
import com.kzadha.model.User;
import com.kzadha.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userDAO;

    @Override
    public List<User> getAll() {
        List<User> users = userDAO.findAll();
        users.forEach(s-> s.setPassword(""));
        return users;
    }
}
