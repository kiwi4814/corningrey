package com.zr.corningrey;

import com.zr.corningrey.user.model.UserEntity;
import com.zr.corningrey.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("cpm.zr.corningrey.**.dao")
class UserTests {
    @Autowired
    private UserService userService;

    @Test
    void addUser() {
        List<UserEntity> userEntityList = userService.findAllUser();
        System.out.println(111);
    }

}
