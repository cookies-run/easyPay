package com.pay.user.service;

import com.pay.core.entity.JsonResult;
import com.pay.user.entity.User;

/**
 * Created by yu on 2018/3/24.
 */
public interface LoginService {

    User doLoginIn(User userLogin);

    JsonResult register(User user);

    int addAliAuthToken(String phone, String appAuthToken);

    JsonResult getUserList();

    JsonResult deleteUser(String phone);
}
