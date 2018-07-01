package com.pay.school.controller;

import com.github.pagehelper.PageInfo;
import com.pay.core.entity.JsonResult;
import com.pay.school.entity.StudentBill;
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
    public JsonResult getListBill(){

        return schoolService.getListBill();

    }

    @RequestMapping(value = "/getStudentBill")
    @ResponseBody
    public JsonResult getStudentBill(Integer id, Integer pageNo, Integer pageSize){
        PageInfo<StudentBill> real = schoolService.getStudentBill(id,pageNo,pageSize);
        JsonResult jsonResult = new JsonResult(real,"查询成功",true);
        return jsonResult;
    }


}
