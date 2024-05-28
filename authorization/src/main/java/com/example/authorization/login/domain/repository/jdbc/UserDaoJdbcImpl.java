package com.example.authorization.login.domain.repository.jdbc;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.authorization.login.domain.model.User;
import com.example.authorization.login.domain.repository.UserDao;
@Repository
public class UserDaoJdbcImpl implements UserDao {
    @Autowired
    JdbcTemplate jdbc;
    // Get the number of user table.
    // Get the number of user table.
    @Override
    public int count() throws DataAccessException {
        try {
            // Get all and count
            return jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
        } catch (DataAccessException e) {
            // Handle exception (log it and rethrow)
            // Logger can be used here for logging
            throw new DataAccessException("Error counting users", e) {};
        }
    }

    // Insert 1 record in user table.
    @Override
    public int insertOne(User user) throws DataAccessException {
// Point: insert
// Register one record
        int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
                        + " password,"
                        + " user_name,"
                        + " birthday,"
                        + " age,"
                        + " marriage,"
                        + " role)"
                        + " VALUES(?, ?, ?, ?, ?, ?, ?)"
                , user.getUserId()
                , user.getPassword()
                , user.getUserName()
                , user.getBirthday()
                , user.getAge()
                , user.isMarriage()
                , user.getRole());
        return rowNumber;
    }
    // Get 1 record of user table.
    @Override
    public User selectOne(String userId) throws DataAccessException {
        try {
            // Get one record
            String sql = "SELECT * FROM m_user WHERE user_id = ?";
            Map<String, Object> map = jdbc.queryForMap(sql, userId);

            User user = new User();
            user.setUserId((String) map.get("user_id"));
            user.setPassword((String) map.get("password"));
            user.setUserName((String) map.get("user_name"));
            user.setBirthday((Date) map.get("birthday"));
            user.setAge((Integer) map.get("age"));
            user.setMarriage((Boolean) map.get("marriage"));
            user.setRole((String) map.get("role"));

            return user;
        } catch (DataAccessException e) {
            // Handle exception (log it and rethrow)
            throw new DataAccessException("Error selecting user", e) {};
        }
    }
    // Get all data of user table.
    @Override
    public List<User> selectMany() throws DataAccessException {
        try {
            // Get all records
            List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
            // Variable for returning results
            List<User> userList = new ArrayList<>();
            // Store the get records in a List for returning results
            for (Map<String, Object> map : getList) {
                // Create User instance
                User user = new User();
                // Set the get record in User instance
                user.setUserId((String) map.get("user_id"));
                user.setPassword((String) map.get("password"));
                user.setUserName((String) map.get("user_name"));
                user.setBirthday((Date) map.get("birthday"));
                user.setAge((Integer) map.get("age"));
                user.setMarriage((Boolean) map.get("marriage"));
                user.setRole((String) map.get("role"));
                // Add to List
                userList.add(user);
            }
            return userList;
        } catch (DataAccessException e) {
            // Handle exception (log it and rethrow)
            throw new DataAccessException("Error selecting users", e) {};
        }
    }
    // Update 1 record in user table.
    @Override
    public int updateOne(User user) throws DataAccessException {
        try {
            // Update one record
            return jdbc.update("UPDATE m_user SET password = ?, user_name = ?, birthday = ?, age = ?, marriage = ?, role = ? WHERE user_id = ?",
                    user.getPassword(),
                    user.getUserName(),
                    user.getBirthday(),
                    user.getAge(),
                    user.isMarriage(),
                    user.getRole(),
                    user.getUserId());
        } catch (DataAccessException e) {
            // Handle exception (log it and rethrow)
            throw new DataAccessException("Error updating user", e) {};
        }
    }

    // Delete 1 record from user table.
    @Override
    public int deleteOne(String userId) throws DataAccessException {
        try {
            // Delete one record
            return jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);
        } catch (DataAccessException e) {
            // Handle exception (log it and rethrow)
            throw new DataAccessException("Error deleting user", e) {};
        }
    }


    // Save the SQL execution result in CSV on the server.
    @Override
    public void userCsvOut() throws DataAccessException {
        String sql = "SELECT * FROM m_user";
        jdbc.query(sql, new UserRowCallbackHandler());
    }


}