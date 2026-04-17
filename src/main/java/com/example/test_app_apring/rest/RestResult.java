package com.example.test_app_apring.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RestResult {
    //リターンコード
    private int result;

    //エラーマップ
    private Map<String, String> errors;
}
