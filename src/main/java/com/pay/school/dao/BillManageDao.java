package com.pay.school.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by cy on 2018/6/18.
 */
@Repository
public interface BillManageDao {
    int deleteBillById(@Param("billId") Integer billId);
    int deleteBillDetaileByBillId(@Param("billId") Integer billId);
}
