package com.example.springboot.service;

import com.example.springboot.entity.user;
import com.example.springboot.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserLoginService {

    /**
     * 注入dao
     */
    @Autowired
    private userMapper usermapper;

    //用户登录
    public user userLogin(user dto){

        user user = usermapper.userlogin(dto);
        return user;
    }

    //注册新用户
    public int adduser(user dto){


        return usermapper.adduser(dto);
        //return usermapper.adduser1(username,password,age);     //对应sql语句中的第二种注册方式
    }
}

