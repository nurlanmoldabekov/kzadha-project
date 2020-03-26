package com.kzadha.mapper;

import com.kzadha.model.User;
import com.kzadha.model.enums.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        return User.builder().id(rs.getLong("id"))
                .email(rs.getString("vemail"))
                .password(rs.getString("vpassword"))
                .name(rs.getString("vname"))
                .roles(UserRole.valueOf(rs.getString("vroles")))
                .build();
    }
}
