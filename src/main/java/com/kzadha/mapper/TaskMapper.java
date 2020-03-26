package com.kzadha.mapper;

import com.kzadha.model.Task;
import com.kzadha.model.enums.TaskStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper {
    @Override
    public Task mapRow(ResultSet rs, int i) throws SQLException {

        return Task.builder().id(rs.getLong("id"))
                .createDate(rs.getDate("dcreatedate"))
                .updateDate(rs.getDate("dupdatedate"))
                .userId(rs.getLong("nuserid"))
                .executorUserId(rs.getLong("nexecutoruserid"))
                .description(rs.getString("vdescription"))
                .status(TaskStatus.valueOf(rs.getString("vstatus")))
                .build();
    }
}
