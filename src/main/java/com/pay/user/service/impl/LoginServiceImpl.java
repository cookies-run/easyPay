package com.pay.user.service.impl;

import com.pay.core.entity.JsonResult;
import com.pay.user.dao.LoginDao;
import com.pay.user.entity.User;
import com.pay.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pay.common.shiro.ShiroUtils.getShiroUser;

/**
 * Created by yu on 2018/3/24.
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDao loginDao;


    @Override
    public User doLoginIn(User user) {
        List<User> list = loginDao.selectId(user.getPhone());
        if(list.size()==0){
            return null;
        }else{
            if(user.getPassword().equals(list.get(0).getPassword())){
                return list.get(0);
            }else{
                return null;
            }
        }
    }

    //注册判断
    @Override
    public JsonResult register(User user) {
        JsonResult jsonResult = null;

        int i = loginDao.register(user);
        if(i>0){
            jsonResult = new JsonResult(i,"新增用户成功",true);
        }else {
            jsonResult = new JsonResult(null,"新增失败",false);
        }
        return jsonResult;
    }

    //保存当前用户的appAuthToken
    @Override
    public int addAliAuthToken(String phone, String appAuthToken) {
        return loginDao.addAliAuthToken(phone,appAuthToken);
    }


    @Override
    public JsonResult getUserList() {
        JsonResult jsonResult = null;
        User user = getShiroUser();
        String role = user.getRole();
        String schoolAccount = user.getPhone();
        List<User> users = loginDao.getUserList(role,schoolAccount);
        if(users.size()>0){
            jsonResult = new JsonResult(users,"用户列表",true);
        }else{
            jsonResult = new JsonResult(null,"暂无数据",true);
        }

        return jsonResult;
    }

    @Override
    public JsonResult deleteUser(String phone) {
        JsonResult jsonResult = null;
        int i = loginDao.deleteUser(phone);
        if(i>0){
            jsonResult = new JsonResult(null,"删除成功",true);
        }else{
            jsonResult = new JsonResult(null,"删除失败",false);
        }
        return jsonResult;
    }
}
