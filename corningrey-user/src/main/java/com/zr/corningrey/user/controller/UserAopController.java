package com.zr.corningrey.user.controller;

import com.zr.corningrey.aop.ControllerWebLog;
import com.zr.corningrey.user.model.UserEntity;
import com.zr.corningrey.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/aop")
public class UserAopController {
    @Resource
    private UserService userService;

    @GetMapping("/userAll")
    @ControllerWebLog(name = "查询", intoDb = true)
    public List<UserEntity> findALl() {
        return userService.findAllUser();
    }
}
