package com.zr.corningrey.user.service;

import com.zr.corningrey.user.model.UserEntity;

import java.util.List;


public interface UserService {
    List<UserEntity> findAllUser();
}
