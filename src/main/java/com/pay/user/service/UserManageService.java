package com.pay.user.service;

import com.pay.core.entity.JsonResult;
import com.pay.user.entity.User;

/**
 * Created by cy on 2018/6/18.
 */
public interface UserManageService {
    JsonResult editUserPwd(User user);
    JsonResult addChildAccount(User user);
}
