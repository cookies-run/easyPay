package com.pay.user.dao;

import com.pay.core.entity.JsonResult;
import com.pay.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yu on 2018/3/24.
 */
@Repository
public interface LoginDao {

    List<User> selectId(String phone);

    int addAliAuthToken(@Param("phone") String phone, @Param("appAuthToken") String appAuthToken);

    int register(@Param("user")User user);

    List<User> getUserList();

    int deleteUser(String phone);

    void updateSchoolNo(@Param("phone") String phone, @Param("schoolNo") String schoolNo);
}
