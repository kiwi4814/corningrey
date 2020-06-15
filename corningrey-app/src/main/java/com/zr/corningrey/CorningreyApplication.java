package com.zr.corningrey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author heqifeng
 */
@SpringBootApplication
@MapperScan("com.zr.corningrey.**.dao")
public class CorningreyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorningreyApplication.class, args);
    }

}
