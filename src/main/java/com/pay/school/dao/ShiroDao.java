package com.pay.school.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by yu on 2018/6/2.
 */
@Repository
public interface ShiroDao {
    String getShiroAppAuthToken(String phone);

    String getShiroSchoolNo(String phone);
}
