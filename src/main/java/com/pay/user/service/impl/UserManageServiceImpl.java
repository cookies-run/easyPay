package com.pay.user.service.impl;

import com.pay.core.entity.JsonResult;
import com.pay.user.dao.UserManageDao;
import com.pay.user.entity.User;
import com.pay.user.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cy on 2018/6/18.
 */
@Service
public class UserManageServiceImpl implements UserManageService{
    @Autowired
    UserManageDao userManageDao;
    public JsonResult editUserPwd(User user){
        JsonResult jsonResult = null;
        //1.判断是否存在该用户和密码
        String phone = user.getPhone();
        String oldPwd = user.getPassword();
        String newPwd = user.getNewPassword();
        String surePwd = user.getSurePassword();
        int userCount = userManageDao.isExistUser(phone,oldPwd);
        if(userCount==1){
            //2.新密码和确认密码是否一致
            if(newPwd!=null&&newPwd.length()>0){
                if(newPwd.equals(surePwd)){
                    //3.更新老密码
                    int updateCount = userManageDao.updateOldPwd(phone,newPwd);
                    if(updateCount>0){
                        jsonResult = new JsonResult(null,"修改成功！",true);
                    }else{
                        jsonResult = new JsonResult(null,"修改失败！",false);
                    }
                }else{
                    jsonResult = new JsonResult(null,"新密码和确认密码不一致！",false);
                }
            }else{
                jsonResult = new JsonResult(null,"请输入新密码！",false);
            }

        }else{
            jsonResult = new JsonResult(null,"原密码不正确！",false);
        }
        return jsonResult;
    }
    public JsonResult addChildAccount(User user){
        JsonResult jsonResult = null;
        String password = user.getPassword();
        String surePassword = user.getSurePassword();
        if(password!=null){
            if(!password.equals(surePassword)){
                jsonResult = new JsonResult(null,"两次输入密码不一致！",false);
            }else{
                int addCount = userManageDao.addChildAccount(user);
                if(addCount>0){
                    jsonResult = new JsonResult(null,"添加子账号成功！",true);
                }else{
                    jsonResult = new JsonResult(null,"添加子账号失败！",false);
                }
            }
        }
        return jsonResult;
    }
    public JsonResult addAgentUser(User user){
        JsonResult jsonResult = new JsonResult(null,"添加代理商账号失败！",false);
        String password = user.getPassword();
        String surePassword = user.getSurePassword();
//        if(password!=null){
//            if(!password.equals(surePassword)){
//                jsonResult = new JsonResult(null,"两次输入密码不一致！",false);
//            }else{
//                int addCount = userManageDao.addAgentUser(user);
//                if(addCount>0){
//                    jsonResult = new JsonResult(null,"添加代理商账号成功！",true);
//                }else{
//                    jsonResult = new JsonResult(null,"添加代理商账号失败！",false);
//                }
//            }
//        }
            int addCount = userManageDao.addAgentUser(user);
            if(addCount>0){
                jsonResult = new JsonResult(null,"添加代理商账号成功！",true);
            }else{
                jsonResult = new JsonResult(null,"添加代理商账号失败！",false);
            }
        return jsonResult;
    }


}
