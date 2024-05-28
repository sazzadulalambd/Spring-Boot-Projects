package com.example.authorization.login.domain.service;
import com.example.authorization.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import com.example.authorization.login.domain.repository.UserDao;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class UserService {
    @Autowired
//    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    // Method for insert.
    public boolean insert(User user) {
        // Execute insert
        int rowNumber = dao.insertOne(user);
        // Judgment variable
        boolean result = false;
        if (rowNumber > 0) {
            // insert success
            result = true;
        }
        return result;
    }

    // Method for count.
    public int count() {
        return dao.count();
    }
    // Method to get all records.
    public List<User> selectMany() {
        // Get all records
        return dao.selectMany();
    }
    // Method to get one record.
    public User selectOne(String userId) {
        // Execute selectOne
        return dao.selectOne(userId);
    }

    // Method for updating one record.
    public boolean updateOne(User user) {
        // Update one record
        int rowNumber = dao.updateOne(user);
        // Judgment variable
        boolean result = false;
        if (rowNumber > 0) {
            // Update success
            result = true;
        }
        return result;
    }

    // Method for deleting one record.
    public boolean deleteOne(String userId) {
        // Delete one record
        int rowNumber = dao.deleteOne(userId);
        // Judgment variable
        boolean result = false;
        if (rowNumber > 0) {
            // Delete success
            result = true;
        }
        return result;
    }

    // Export user list as CSV.
    public void userCsvOut() throws DataAccessException {
        dao.userCsvOut();
    }

    public byte[] getFile(String fileName) throws IOException {
        FileSystem fs = FileSystems.getDefault();
        Path p = fs.getPath(fileName);
        return Files.readAllBytes(p);
    }
}