package com.zr.corningrey.user.dao;

import com.zr.corningrey.user.model.UserEntity;

import java.util.List;

public interface UserMapper {
    List<UserEntity> findUserAll();
}