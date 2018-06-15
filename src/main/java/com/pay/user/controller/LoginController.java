package com.pay.user.controller;

import com.pay.core.entity.JsonResult;
import com.pay.user.entity.User;
import com.pay.user.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by yu on 2018/3/24.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/loginIn")
    @ResponseBody
    public JsonResult loginIn(User user) {
        JsonResult jsonResult = null;
        String info = loginUser(user);
        if (!"SUCC".equals(info)) {
            //model.addAttribute("failMsg", "User does not exist or password error!");
            jsonResult = new JsonResult(null,info,false);
        } else {
            Subject subject = SecurityUtils.getSubject();
            User us = (User) subject.getPrincipal();
            jsonResult = new JsonResult(us,"登录成功",true);
        }
        return jsonResult;
    }

    private String loginUser(User user) {
        if (isRelogin(user)){
            return "SUCC"; //
        }


        return shiroLogin(user); //
    }

    private boolean isRelogin(User user) {
        Subject us = SecurityUtils.getSubject();
        if (us.isAuthenticated()) {
            return true; //
        }
        return false; //
    }

    //真正的登录
    private String shiroLogin(User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(), user.getPassword().toCharArray(), null);
        token.setRememberMe(true);

        // shiro
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException ex) {
            return "用户不存在或密码错误!";
        } catch (IncorrectCredentialsException ex) {
            return "用户不存在或密码错误!";
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            return ex.getMessage(); //
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Internal error, please try again!";
        }
        return "SUCC";
    }

    //注册用户信息
    @RequestMapping(value = "/register")
    @ResponseBody
    private JsonResult register(User user){
        //验证手机
        //验证用户名
        //验证
        return loginService.register(user);
    }

    @RequestMapping(value = "/logout")
    private void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    private JsonResult getUserList(){
        return loginService.getUserList();
    }

    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    private JsonResult deleteUser(String phone){
        return loginService.deleteUser(phone);
    }



}
