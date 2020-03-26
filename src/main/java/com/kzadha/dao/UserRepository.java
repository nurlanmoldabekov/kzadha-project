package com.kzadha.dao;

import com.kzadha.mapper.UserMapper;
import com.kzadha.model.User;
import com.kzadha.model.enums.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class UserRepository {
    private final JdbcTemplate template;
    private static final String GET_ALL = "SELECT * FROM t_users;";
    private static final String GET_BY_ID = "SELECT * FROM t_users WHERE id=?";
    private static final String GET_BY_ROLES = "SELECT * FROM t_users WHERE vroles=?";
    private static final String GET_BY_EMAIL = "SELECT * FROM t_users WHERE vemail=?";
    private static final String INSERT = "INSERT INTO t_users(vname, vpassword, vemail,vroles) VALUES (?,?,?,?)";


    public User findById(Long id){
        return (User) template.queryForObject(GET_BY_ID, new UserMapper(), id);
    }

    public List<User> findAll(){
        return template.query(GET_ALL, new UserMapper());
    }

    public List<User> findByRole(UserRole role){
        return template.query(GET_BY_ROLES, new UserMapper(), role.name());
    }
    public User findByEmail(String email){
        return (User) template.queryForObject(GET_BY_EMAIL, new UserMapper(), email);
    }

    public void insert(User input){
        template.update(INSERT, input.getName(), input.getPassword(), input.getEmail(), input.getRoles().name());
    }



}
