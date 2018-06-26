package com.pay.bill.service;

import com.pay.core.entity.JsonResult;

/**
 * Created by yu on 2018/6/15.
 */
public interface BillService {

    JsonResult closeBill(Integer id);

    boolean saveBillTradeNo(String trade_no,String out_trade_no,String trade_status);
}
