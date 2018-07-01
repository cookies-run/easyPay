package com.pay.school.service.impl;

import com.pay.common.utils.ExcelUtil;
import com.pay.core.entity.JsonResult;
import com.pay.school.dao.BillManageDao;
import com.pay.school.dao.SchoolDao;
import com.pay.school.entity.StudentBill;
import com.pay.school.service.BillManageService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cy on 2018/6/18.
 */
@Service
public class BillManageServiceImpl implements BillManageService {
    @Autowired
    BillManageDao billManageDao;
    @Autowired
    SchoolDao schoolDao;

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
    public JsonResult billExport(Integer billId){
        JsonResult jsonResult = new JsonResult(null,"账单导出失败！",false);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            //根据账单id获取账单详情列表
            List<StudentBill> studentBills = schoolDao.getStudentBillById(billId);
            if(studentBills.isEmpty()){
                return null;
            }
            //excel标题
            String[] title = {"姓名","金额","账单状态"};
            //excel文件名
            String fileName = studentBills.get(0).getChargeBillTitle()+format.format(new Date())+".xls";
            //sheet名
            String sheetName = "账单明细";
            String content[][] = new String[studentBills.size()][2];
            for (int i = 0; i < studentBills.size(); i++) {
                StudentBill studentBill = studentBills.get(i);
                content[i][0] = studentBill.getChildName();
                content[i][1] = studentBill.getAmount().toString();
            }
            //创建HSSFWorkbook
            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
            File file = new File("C:/studentExcel");
            if(!file.isFile()){
                file.mkdir();
            }
            FileOutputStream out = new FileOutputStream(file+"/"+fileName);
            wb.write(out);
            out.flush();
            try{
                out.close();

            }catch (IOException e){
                jsonResult.setMsg("导出账单详情异常！"+e);
            }
            jsonResult.setMsg("导出成功！");
            jsonResult.setSuc(true);
        }catch (Exception e){
            jsonResult.setMsg("导出账单详情异常！"+e);
        }
        return jsonResult;
    }
}
