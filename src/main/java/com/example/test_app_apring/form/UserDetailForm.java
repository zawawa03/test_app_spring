package com.example.test_app_apring.form;

import com.example.test_app_apring.domain.user.model.Department;
import lombok.Data;

import java.util.Date;

@Data
public class UserDetailForm {
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Department department;
}
