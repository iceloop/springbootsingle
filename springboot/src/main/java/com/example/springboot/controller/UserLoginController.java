package com.example.springboot.controller;
import com.example.springboot.entity.user;
import com.example.springboot.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * Demo class user
 *
 * @author hrniu
 * @date 2019/3/13
 */
@Controller
@RequestMapping(value = {"/user"})
public class UserLoginController {

    /**
     * 注入service
     */
    @Autowired
    private UserLoginService userLoginService;

    /**
     * 跳转到用户登录页面
     * @return 登录页面
     */
    @RequestMapping(value = {"/loginHtml"})
    public String loginHtml(){
        return "userLogin";
    }

    /**
     * 跳转到用户注册页面
     * @return 注册页面
     */
    @RequestMapping(value = {"/registerpage"})
    public String registerpage(){
        return "register";
    }

    /**
     * 获取用户名与密码，用户登录
     * @return 登录成功页面
     * Transactional  注解事务
     */
    @RequestMapping(value = {"/userLogin"})
    @Transactional(rollbackFor = Exception.class)
    public String userLogin(user dto, HttpServletRequest request){

        user user = userLoginService.userLogin(dto);
        //登录成功
        if(user != null){
            //将用户信息放入session
            request.getSession().setAttribute("session_user",user);
            return "index";
        }
        return "loginError";
    }

    /**
     * 注册新用户
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping(value = {"/uregister"})
    public String addUser(user dto){

        if(!dto.getPassword().equals("123")){

            return "两次密码不相同，注册失败！！";
        }else {
            int res = userLoginService.adduser(dto);
            if(res == 0){
                return "注册失败！";
            }else {
                return "注册成功！";
            }
        }

    }
}

