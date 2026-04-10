package com.example.test_app_apring.repository;

import com.example.test_app_apring.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertOne(MUser user);

    List<MUser> findMany();

    MUser findOne(String userId);
}
