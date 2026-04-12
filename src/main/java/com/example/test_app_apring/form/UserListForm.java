package com.example.test_app_apring.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UserListForm {
    private String userId;
    private String userName;
}