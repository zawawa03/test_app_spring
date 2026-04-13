package com.example.test_app_apring.domain.user.service.impl;

import com.example.test_app_apring.domain.user.model.MUser;
import com.example.test_app_apring.domain.user.service.UserService;
import com.example.test_app_apring.repository.UserMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;

    public UserServiceImpl(UserMapper mapper) {this.mapper = mapper;}

    @Override
    public void signup(MUser user) {
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");
        mapper.insertOne(user);
    }

    @Override
    public List<MUser> getUsers(MUser user) {
        return mapper.findMany(user);
    }

    @Override
    public MUser getUserOne(String userId) {
        return mapper.findOne(userId);
    }

    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
        mapper.updateOne(userId, password, userName);
    }

    @Override
    public void deleteUserOne(String userId) {
        mapper.deleteOne(userId);
    }
}