package com.pay.school.service.impl;

import com.pay.core.entity.JsonResult;
import com.pay.school.dao.BillManageDao;
import com.pay.school.service.BillManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cy on 2018/6/18.
 */
@Service
public class BillManageServiceImpl implements BillManageService {
    @Autowired
    BillManageDao billManageDao;
    public JsonResult billDelete(Integer billId){
        JsonResult jsonResult = null;
        //删除账单总表
        int billCount = billManageDao.deleteBillById(billId);
        //删除账单详情表
        int billDetaileCount = billManageDao.deleteBillDetaileByBillId(billId);
        if(billCount>0){
            jsonResult = new JsonResult(null,"删除账单成功！",true);
        }
        else{
            jsonResult = new JsonResult(null,"删除账单失败！",false);
        }
        return jsonResult;
    }
}
