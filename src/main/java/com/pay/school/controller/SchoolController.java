package com.pay.school.controller;

import com.pay.core.entity.JsonResult;
import com.pay.school.entity.SchoolInfo;
import com.pay.school.service.SchoolService;
import com.pay.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.pay.common.shiro.ShiroUtils.getShiroUser;

/**
 * Created by yu on 2018/3/7.
 */

@Controller
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/getSchool/{id}")
    @ResponseBody
    public SchoolInfo getSchoolById(@PathVariable("id") Integer id) {
        return schoolService.getSchoolById(id);
    }

    //保存学校信息 & 获取学校编码
    @RequestMapping(value = "/addSchoolInfoAndGetSchoolNo")
    @ResponseBody
    public JsonResult addSchoolInfo(SchoolInfo school) {

        JsonResult jsonResult = schoolService.addSchoolInfo(school);
        return jsonResult;

    }

    //获取学校编码
    @RequestMapping(value = "/getSchoolNo")
    @ResponseBody
    public JsonResult getSchoolNo(String phone){

        return null;
    }

    //获取学校信息
    @RequestMapping(value = "/getSchoolInfo")
    @ResponseBody
    public JsonResult getSchoolInfo(){

        User user = getShiroUser();
        JsonResult jsonResult = schoolService.getSchoolInfo(user);
        return jsonResult;
    }




}
