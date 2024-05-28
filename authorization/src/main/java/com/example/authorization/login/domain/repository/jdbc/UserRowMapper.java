package com.example.authorization.login.domain.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.example.authorization.login.domain.model.User;
// Point: RowMapper
public class UserRowMapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
// Create User instance for return value
        User user = new User();
// Set the get result of ResultSet to User instance
        user.setUserId(rs.getString("user_id"));
        user.setPassword(rs.getString("password"));
        user.setUserName(rs.getString("user_name"));
        user.setBirthday(rs.getDate("birthday"));
        user.setAge(rs.getInt("age"));
        user.setMarriage(rs.getBoolean("marriage"));
        user.setRole(rs.getString("role"));
        return user;
    }
}