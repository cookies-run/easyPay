package com.pay.school.service;

import com.pay.core.entity.JsonResult;
import com.pay.school.entity.Bill;
import com.pay.school.entity.SchoolInfo;
import com.pay.school.entity.StudentBill;
import com.pay.user.entity.User;

import java.util.List;

/**
 * Created by yu on 2018/3/7.
 */
public interface SchoolService {
    SchoolInfo getSchoolById(Integer id);

    JsonResult<String> addSchoolInfo(SchoolInfo school);

    JsonResult addStudentBill(Bill bill, List<StudentBill> studentBills);

    JsonResult getListBill(String phone);

    JsonResult sendBill(Integer id);

    JsonResult getStudentBill(Integer id);

    JsonResult getSchoolInfo(User user);
}
