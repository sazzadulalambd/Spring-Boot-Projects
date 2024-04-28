package com.example.authorization.login.domain.repository.jdbc;
import java.util.List;
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
    @Override
    public int count() throws DataAccessException {
        return 0;
    }
    // Insert 1 record in user table.
    @Override
    public int insertOne(User user) throws DataAccessException {
        return 0;
    }
    // Get 1 record of user table.
    @Override
    public User selectOne(String userId) throws DataAccessException {
        return null;
    }
    // Get all data of user table.
    @Override
    public List<User> selectMany() throws DataAccessException {
        return null;
    }
    // Update 1 record in user table.
    @Override
    public int updateOne(User user) throws DataAccessException {
        return 0;
    }
    // Delete 1 record from user table.
    @Override
    public int deleteOne(String userId) throws DataAccessException {
        return 0;
    }
    // Save the SQL execution result in CSV on the server.
    @Override
    public void userCsvOut() throws DataAccessException {
    }
}