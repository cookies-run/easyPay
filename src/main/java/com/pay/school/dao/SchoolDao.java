package com.pay.school.dao;

import com.pay.school.entity.Bill;
import com.pay.school.entity.SchoolInfo;
import com.pay.school.entity.StudentBill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yu on 2018/3/7.
 */
@Repository
public interface SchoolDao {
    SchoolInfo getSchoolById(Integer id);

    void addSchoolInfo(@Param("school")SchoolInfo school);

    int addStudentBill(@Param("id")int id, @Param("studentBills")List<StudentBill> studentBills);

    int addBill(@Param("bill")Bill bill);

    List<Bill> getListBill(@Param("phone") String phone,@Param("role") String role);

    List<StudentBill> getStudentBillById(@Param("id") Integer id);

    int updateBillById(@Param("id")Integer id, @Param("orderNo")String orderNo);

    int updateBill(int bill_id);

    SchoolInfo getSchoolBySchoolNo(String schoolNo);
}
