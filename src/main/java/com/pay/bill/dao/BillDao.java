package com.pay.bill.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yu on 2018/6/22.
 */
@Repository
public interface BillDao {
    int saveBillTradeNo(@Param("trade_no") String trade_no, @Param("out_trade_no") String out_trade_no, @Param("trade_status") String trade_status);
}
