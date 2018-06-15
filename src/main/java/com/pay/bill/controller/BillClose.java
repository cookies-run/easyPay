package com.pay.bill.controller;

import com.pay.bill.service.BillService;
import com.pay.core.entity.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yu on 2018/6/15.
 */
@Controller
@RequestMapping("/close")
public class BillClose {

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/closeBill")
    @ResponseBody
    public JsonResult closeBill(Integer id){
        return billService.closeBill(id);
    }


}
