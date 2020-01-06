package com.matas.dao;

import com.matas.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from user where id= #{id}")
    User findById(Integer id);

    @Select("select * from user")
    List<User> findAll();
}