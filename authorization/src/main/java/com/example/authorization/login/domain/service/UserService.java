package com.example.authorization.login.domain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.authorization.login.domain.repository.UserDao;
@Service
public class UserService {
    @Autowired
    UserDao dao;
}