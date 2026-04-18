package com.example.test_app_apring.domain.user.service.impl;

import com.example.test_app_apring.domain.user.model.MUser;
import com.example.test_app_apring.domain.user.service.UserService;
import com.example.test_app_apring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl2 implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;;

    public UserServiceImpl2(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    //ユーザー登録
    @Transactional
    @Override
    public void signup(MUser user) {
        //存在チェック
        boolean exists = repository.existsById(user.getUserId());
        if(exists){
            throw new RuntimeException("ユーザーが既に存在します"){};
        }

        //部署、ロールセット
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");

        //パスワードセット
        String rawpassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawpassword));

        //insert
        repository.save(user);
    }

    //ユーザー取得
    @Override
    public List<MUser> getUsers(MUser user) {
        return repository.findAll();
    }

    //ユーザー取得１っ件
    @Override
    public MUser getUserOne(String userId) {
        Optional<MUser> option = repository.findById(userId);
        MUser user = option.orElse(null);
        return user;
    }

    //ユーザー更新
    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
      String encryptPassword = passwordEncoder.encode(password);
      repository.updateUser(userId, encryptPassword, userName);
    }

    //ユーザー削除
    @Transactional
    @Override
    public void deleteUserOne(String userId) {
        repository.deleteById(userId);
    }

    //ログインユーザー取得
    @Override
    public MUser getLoginUser(String userId) {
        return repository.findLoginUser(userId);
    }
}