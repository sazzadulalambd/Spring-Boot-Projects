package com.example.authorization.login.domain.repository;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.example.authorization.login.domain.model.User;
public interface UserDao {
    // Point : DataAccessException
// Get the number of user table.
    public int count() throws DataAccessException;
    // Insert 1 record in user table.
    public int insertOne(User user) throws DataAccessException;
    // Get 1 record of user table.
    public User selectOne(String userId) throws DataAccessException;
    // Get all data of user table.
    public List<User> selectMany() throws DataAccessException;
    // Update 1 record in user table.
    public int updateOne(User user) throws DataAccessException;
    // Delete 1 record from user table.
    public int deleteOne(String userId) throws DataAccessException;
    // Save the SQL execution result in CSV on the server.
    public void userCsvOut() throws DataAccessException;
}