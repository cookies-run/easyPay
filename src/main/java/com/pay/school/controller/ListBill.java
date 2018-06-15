package com.pay.school.controller;

import com.pay.core.entity.JsonResult;
import com.pay.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yu on 2018/4/17.
 */
@Controller
@RequestMapping("/bill")
public class ListBill {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/getListBill")
    @ResponseBody
    public JsonResult getListBill(String phone){

        return schoolService.getListBill(phone);

    }

    @RequestMapping(value = "/getStudentBill")
    @ResponseBody
    public JsonResult getStudentBill(Integer id){

        return schoolService.getStudentBill(id);

    }


}
