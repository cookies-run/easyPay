package com.pay.school.controller;

import com.pay.core.entity.JsonResult;
import com.pay.school.service.BillManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cy on 2018/6/18.
 * 删除账单
 */
@Controller
public class BillManageAction {
    @Autowired
    BillManageService billManageService;
    @RequestMapping("billDelete")
    @ResponseBody
    public JsonResult billDelete(Integer billId){
        JsonResult jsonResult = billManageService.billDelete(billId);
        return jsonResult;
    }
    @RequestMapping("billExport")
    @ResponseBody
    public JsonResult billExport(Integer billId){
        return billManageService.billExport(billId);
    }

}
