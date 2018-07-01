package com.pay.user.dao;

import com.pay.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by cy on 2018/6/18.
 */
@Repository
public interface UserManageDao {
    int isExistUser(@Param("phone") String phone, @Param("oldPwd") String oldPwd);
    int updateOldPwd(@Param("phone") String phone, @Param("newPwd") String newPwd);
    int addChildAccount(User user);
    int addAgentUser(User user);
}
