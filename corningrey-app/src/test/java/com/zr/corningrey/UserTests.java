package com.zr.corningrey;

import com.zr.corningrey.user.dao.UserMapper;
import com.zr.corningrey.user.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void addUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("m4814@qq.com");
        int id = userMapper.insert(userEntity);
        assert (1 == id);
    }

}
