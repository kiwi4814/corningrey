package com.zr.corningrey.user.controller;

import com.zr.corningrey.user.model.UserEntity;
import com.zr.corningrey.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("/exc")
public class UserExceptionController {
    @Resource
    private UserService userService;

    @GetMapping("/userAll")
    public List<UserEntity> findALl() {
        throw new RuntimeException("ddd");
    }

    @GetMapping("/userAll1")
    public List<UserEntity> findALl1() {
        System.out.println(1 / 0);
        return new ArrayList<>();
    }
}
