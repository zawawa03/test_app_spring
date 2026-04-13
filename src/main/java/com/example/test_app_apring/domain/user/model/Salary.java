package com.example.test_app_apring.domain.user.model;

import lombok.Data;

@Data
public class Salary {
    private String userId;
    private String yearMonth;
    private Integer salary;
}