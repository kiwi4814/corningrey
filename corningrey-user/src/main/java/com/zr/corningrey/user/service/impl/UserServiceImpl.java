package com.zr.corningrey.user.service.impl;

import com.zr.corningrey.user.dao.UserMapper;
import com.zr.corningrey.user.model.UserEntity;
import com.zr.corningrey.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public List<UserEntity> findAllUser() {
        return userMapper.findUserAll();
    }
}
