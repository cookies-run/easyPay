package com.pay.school.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayEcoEduKtBillingQueryRequest;
import com.alipay.api.request.AlipayEcoEduKtBillingSendRequest;
import com.alipay.api.request.AlipayEcoEduKtSchoolinfoModifyRequest;
import com.alipay.api.response.AlipayEcoEduKtBillingQueryResponse;
import com.alipay.api.response.AlipayEcoEduKtBillingSendResponse;
import com.alipay.api.response.AlipayEcoEduKtSchoolinfoModifyResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pay.alipay.utils.PrivateKeySignature;

import com.pay.core.entity.JsonResult;
import com.pay.school.dao.SchoolDao;
import com.pay.school.dao.ShiroDao;
import com.pay.school.entity.Bill;
import com.pay.school.entity.SchoolInfo;
import com.pay.school.entity.StudentBill;
import com.pay.school.service.SchoolService;
import com.pay.user.dao.LoginDao;
import com.pay.user.entity.User;


import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static com.pay.common.shiro.ShiroUtils.getShiroUser;

/**
 * Created by yu on 2018/3/7.
 */

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private ShiroDao shiroDao;

    private static String ISV_PID = "";
    private static String ISV_PHONE = "";

    static{
        Properties properties = new Properties();
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("key.properties");
            ISV_PID = properties.getProperty("isv.pid");
            ISV_PHONE = properties.getProperty("isv.phone");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public SchoolInfo getSchoolById(Integer id) {
        return schoolDao.getSchoolById(id);
    }

    @Override
    public JsonResult addSchoolInfo(SchoolInfo school) {
        JsonResult jsonResult = null;

        User user = getShiroUser();
        String appAuthToken = shiroDao.getShiroAppAuthToken(user.getPhone());
        //获取学校编码
        AlipayClient alipayClient = PrivateKeySignature.getClient();
        AlipayEcoEduKtSchoolinfoModifyRequest request = new AlipayEcoEduKtSchoolinfoModifyRequest();
        request.putOtherTextParam("app_auth_token", appAuthToken);
        request.setBizContent("{\n" +
                "  \"school_name\":\""+school.getSchoolName()+"\",\n" +
                "  \"school_stdcode\":\""+school.getSchoolStdcode()+"\",\n" +
                "  \"school_type\":\""+getSchoolType(school.getSchoolType())+"\",\n" +
                "  \"province_code\":\""+getAddressCode(school.getProvinceCode())+"\",\n" +
                "  \"province_name\":\""+school.getProvinceName()+"\",\n" +
                "  \"city_code\":\""+getAddressCode(school.getCityCode())+"\",\n" +
                "  \"city_name\":\""+school.getCityName()+"\",\n" +
                "  \"district_code\":\""+getAddressCode(school.getDistrictCode())+"\",\n" +
                "  \"district_name\":\""+school.getDistrictName()+"\",\n" +
                "  \"isv_name\":\""+school.getIsvName()+"\",\n" +
                "  \"isv_notify_url\":\""+school.getIsvNotifyUrl()+"\",\n" +
                "  \"isv_pid\":\""+ISV_PID+"\",\n" +
                "  \"school_pid\":\""+school.getSchoolPid()+"\",\n" +
                "  \"isv_phone\":\""+ISV_PHONE+"\",\n" +
                "}");//设置业务参数
        try {
            AlipayEcoEduKtSchoolinfoModifyResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                String schoolNo = response.getSchoolNo();
                school.setSchoolNo(schoolNo);
                schoolDao.addSchoolInfo(school);
                //更新user表
                loginDao.updateSchoolNo(user.getPhone(),schoolNo);

                jsonResult = new JsonResult(schoolNo,"获取学校编码成功",true);
            }else {
                jsonResult = new JsonResult(null,"获取学校编码失败"+response.getMsg(),false);
            }
        } catch (AlipayApiException e) {
            jsonResult = new JsonResult(null,e.getMessage(),false);
            e.printStackTrace();
        }

        return jsonResult;
    }

    @Override
    public JsonResult addStudentBill(Bill bill, List<StudentBill> studentBills) {
        JsonResult jsonResult = null;
        //先插入主表账单信息
        int id = schoolDao.addBill(bill);
        if(id>0){
           int num = schoolDao.addStudentBill(bill.getId(),studentBills);
           if(num>0){
               jsonResult = new JsonResult(null,"导入成功",true);
           }else{
               jsonResult = new JsonResult(null,"导入失败，请检查EXCEL文件格式",false);
           }
        }else{
            jsonResult = new JsonResult(null,"插入账单失败",false);
        }
        return jsonResult;
    }

    @Override
    public JsonResult getListBill(String phone) {
        JsonResult jsonResult = null;
        List<Bill> bills = schoolDao.getListBill(phone);
        if(bills!=null){
            jsonResult = new JsonResult(bills,"查询成功",true);
        }else {
            jsonResult = new JsonResult(null,"暂无未提交的账单",false);
        }
        return jsonResult;
    }

    @Override
    public JsonResult sendBill(Integer id) {
        JsonResult jsonResult = null;
        boolean flag = true;
        List<StudentBill> studentBills = schoolDao.getStudentBillById(id);
        User user = getShiroUser();
        String appAuthToken = shiroDao.getShiroAppAuthToken(user.getPhone());
        String schoolNo = shiroDao.getShiroSchoolNo(user.getPhone());
        SchoolInfo schoolInfo = schoolDao.getSchoolBySchoolNo(schoolNo);
        if(null!=studentBills&&studentBills.size()>0){
            //发送账单
            int bill_id = studentBills.get(0).getBillId();
            String school_pid = schoolInfo.getSchoolPid();
            String partner_id = ISV_PID;
            AlipayClient alipayClient = PrivateKeySignature.getClient();
            for(StudentBill s: studentBills){
                AlipayEcoEduKtBillingSendRequest request = new AlipayEcoEduKtBillingSendRequest();
                request.putOtherTextParam("app_auth_token", appAuthToken);
                request.setBizContent("{" +
                        "\"users\":"+s.getUsers()+","+
                        "\"school_pid\":\""+school_pid+"\"," +
                                "\"school_no\":\""+schoolNo+"\"," +
                                "\"child_name\":\""+s.getChildName()+"\"," +
                                "\"grade\":\""+s.getGrade()+"\"," +
                                "\"class_in\":\""+s.getClassIn()+"\"," +
                                "\"student_code\":\""+s.getStudentCode()+"\"," +
                                "\"student_identify\":\""+s.getStudentIdentify()+"\"," +
                                "\"out_trade_no\":\""+s.getOutTradeNo()+"\"," +
                                "\"charge_bill_title\":\""+s.getChargeBillTitle()+"\"," +
                                "\"charge_type\":\""+s.getChargeType()+"\"," +
                                "\"charge_item\":" +
                                s.getChargeItem()+","+
                                "\"amount\":"+s.getAmount()+"," +
                                "\"gmt_end\":\""+s.getGmtEnd()+"\"," +
                                "\"end_enable\":\""+s.getEndEnable()+"\"," +
                                "\"partner_id\":\""+partner_id+"\"" +
                                "  }");
                try {
                    AlipayEcoEduKtBillingSendResponse response = alipayClient.execute(request);
                    if(response.isSuccess()){
                        //
                        //插入order_no值
                       int i = schoolDao.updateBillById(s.getId(),response.getOrderNo());
                    }else{
                        flag = false;
                        jsonResult = new JsonResult(null,"账单失败",false);
                    }
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }
            }
            if(!flag){
                jsonResult = new JsonResult(null,"发送失败",false);
            }else {
                //更新bill的状态
                int i = schoolDao.updateBill(bill_id);
                jsonResult = new JsonResult(null,"发送成功",true);
            }
        }
        return jsonResult;
    }

    @Override
    public PageInfo<StudentBill> getStudentBill(Integer id,Integer pageNo,Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize ==null?10:pageSize;
        PageHelper.startPage(pageNo,pageSize);
        List<StudentBill> studentBills = schoolDao.getStudentBillById(id);
        //用PageInfo对结果进行包装
        PageInfo<StudentBill> realStudentBills = new PageInfo<StudentBill>(studentBills);

        AlipayClient alipayClient = PrivateKeySignature.getClient();
        User user = getShiroUser();
        String schoolNo = shiroDao.getShiroSchoolNo(user.getPhone());
        String schoolPId = shiroDao.getShiroSchoolPId(schoolNo);
        for(StudentBill bean: realStudentBills.getList()){
            //循环查询账单状态
            AlipayEcoEduKtBillingQueryRequest request = new AlipayEcoEduKtBillingQueryRequest();
            request.setBizContent("{" +
                    "\"isv_pid\":\""+ISV_PID+"\"," +
                    "\"school_pid\":\""+schoolPId+"\"," +
                    "\"out_trade_no\":\""+bean.getOutTradeNo()+"\"" +
                    "  }");
            try {
                AlipayEcoEduKtBillingQueryResponse response = alipayClient.execute(request);
                if(response.isSuccess()){
                    bean.setOrderStatus(response.getOrderStatus());
                } else {
                    bean.setOrderStatus("UNKNOWN");
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
        }
//        if(null!=studentBills&&studentBills.size()>0){
//            jsonResult = new JsonResult(studentBills,"查询成功",true);
//        }else{
//            jsonResult = new JsonResult(null,"查询不到未处理的账单，请联系管理员",false);
//        }
        return realStudentBills;
    }

    /**
     * 获取学校信息
     * @param user
     * @return
     */
    @Override
    public JsonResult getSchoolInfo(User user) {
        JsonResult jsonResult = null;
        String schoolNo = shiroDao.getShiroSchoolNo(user.getPhone());
        SchoolInfo schoolInfo = schoolDao.getSchoolBySchoolNo(schoolNo);
        if(null!=schoolInfo){
            jsonResult = new JsonResult(schoolInfo,"学校信息",true);
        }
        return jsonResult;
    }

    public String getAddressCode(String code){
        if(code.length()<6){
            code = code+"000000";
        }
        return code.substring(0,6);
    }

    public String getSchoolType(String type){
        String schoolType = "";
        schoolType = type.replaceAll(",","");
        return schoolType;
    }

}
