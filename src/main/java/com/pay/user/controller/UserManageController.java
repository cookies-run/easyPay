package com.pay.user.controller;

import com.pay.core.entity.JsonResult;
import com.pay.user.entity.User;
import com.pay.user.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cy on 2018/6/18.
 */
@Controller
public class UserManageController {
    @Autowired
    UserManageService userManageService;
    @RequestMapping("editUserPwd")
    @ResponseBody
    public JsonResult editUserPwd(User user){
        return userManageService.editUserPwd(user);
    }
    @RequestMapping("addChildAccount")
    @ResponseBody
    public JsonResult addChildAccount(User user){
        return userManageService.addChildAccount(user);
    }

    /**
     * 添加代理商账号
     * @param user
     * @return
     */
    @RequestMapping("addAgentUser")
    @ResponseBody
    public JsonResult addAgentUser(User user){
        return userManageService.addAgentUser(user);
    }
}
