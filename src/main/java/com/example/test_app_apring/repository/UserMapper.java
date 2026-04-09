package com.example.test_app_apring.repository;

import com.example.test_app_apring.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertOne(MUser user);
}
