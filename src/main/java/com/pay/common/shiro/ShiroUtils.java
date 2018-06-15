package com.pay.common.shiro;

import com.pay.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yu on 2018/5/29.
 */
public class ShiroUtils {


    //获取用户基础信息
    public static User getShiroUser(){

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("easy");
        return user;
    }

}
