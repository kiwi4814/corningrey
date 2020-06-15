package com.zr.corningrey.user.controller;

import com.zr.corningrey.user.model.UserEntity;
import com.zr.corningrey.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/userAll")
    public List<UserEntity> findALl() {
        return userService.findAllUser();
    }
}
