package com.example.test_app_apring.domain.user.service;

import com.example.test_app_apring.domain.user.model.MUser;

import java.util.List;

public interface UserService {
    public void signup(MUser user);

    public List<MUser> getUsers();

    public MUser getUserOne(String userId);

    public void updateUserOne(String userId, String password, String username);

    public void deleteUserOne(String userId);
}