package com.example.test_app_apring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HelloController {

    private final EmployeeService service;

    public HelloController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }

    @PostMapping("/hello")
    public String postRequest(@RequestParam("name") String str, Model model) {
        model.addAttribute("sample", str);
        return "response";
    }

    @PostMapping("/hello/db")
    public String postDbRequest(@RequestParam("textId") String id, Model model) {
        Employee employee = service.getEmployee(id);
        model.addAttribute("employee", employee);
        return "employee/db";
    }
}