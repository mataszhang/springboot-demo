package com.matas;

import com.matas.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class MybatisApplication implements CommandLineRunner {
    @Resource
    private UserDao userMapper;


    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        this.userMapper.findAll().forEach(user -> log.info("查询h2数据库中数据=>" + user));
    }
}