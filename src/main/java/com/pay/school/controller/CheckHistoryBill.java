package com.pay.school.controller;

import com.pay.core.entity.JsonResult;
import com.pay.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yu on 2018/4/24.
 */
@Controller
@RequestMapping("/check")
public class CheckHistoryBill {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/getBill")
    @ResponseBody
    public JsonResult sendBill(Integer id){
        return schoolService.sendBill(id);
    }
}
