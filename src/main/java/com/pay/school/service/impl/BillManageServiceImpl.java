package com.pay.school.service.impl;

import com.pay.common.utils.ExcelUtil;
import com.pay.core.entity.JsonResult;
import com.pay.school.dao.BillManageDao;
import com.pay.school.dao.SchoolDao;
import com.pay.school.entity.FileOper;
import com.pay.school.entity.StudentBill;
import com.pay.school.service.BillManageService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.pay.common.utils.BillStatus.FormatTradeStatus;

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

        FileOutputStream out = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        try{
            //根据账单id获取账单详情列表
            List<StudentBill> studentBills = schoolDao.getStudentBillById(billId);
            if(studentBills.isEmpty()){
                return null;
            }
            //excel标题
            String[] title = {"姓名","年级","金额","账单状态"};
            //excel文件名
            String fileName = format.format(new Date())+" "+studentBills.get(0).getChargeBillTitle()+".xls";
            //sheet名
            String sheetName = "账单明细";
            String content[][] = new String[studentBills.size()][4];
            for (int i = 0; i < studentBills.size(); i++) {
                StudentBill studentBill = studentBills.get(i);
                content[i][0] = studentBill.getChildName();
                content[i][1] = studentBill.getClassIn();
                content[i][2] = studentBill.getAmount().toString();
                content[i][3] = FormatTradeStatus(studentBill.getTradeStatus());
            }
            //创建HSSFWorkbook
            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
            String rootPath = System.getProperty("java.io.tmpdir").replace("\\","/");
            String filePath = rootPath+"/"+fileName;
            filePath = filePath.replace("\\","/");

            out = new FileOutputStream(filePath);
            wb.write(out);
            out.flush();
            out.close();

            out = new FileOutputStream(filePath);
            wb.write(out);
            out.flush();
            try{
                out.close();

            }catch (IOException e){
                jsonResult.setMsg("导出账单详情异常！"+e);
            }

            FileOper fileOper = new FileOper();
            fileOper.setFileName(fileName);
            fileOper.setFilePath(filePath);
            jsonResult.setMsg("导出成功！");
            jsonResult.setSuc(true);
            jsonResult.setData(fileOper);

        }catch (Exception e){
            jsonResult.setMsg("导出账单详情异常！"+e);
            e.printStackTrace();
        }
        return jsonResult;
    }
  
    public JsonResult downLoadBill(HttpServletResponse response,String fileNmae,String filePath) {
        JsonResult jsonResult = new JsonResult(null,"excel下载失败！",false);
        OutputStream outputStream=null;
        InputStream fileInputStream = null;
       try{
           File file = new File(filePath);
           if (file.exists()) {
               response.setContentType("application/octet-stream");
               response.addHeader("Content-Disposition", "attachment; filename="+fileNmae);
               fileInputStream = new FileInputStream(file);
               byte[] by = new byte[fileInputStream.available()];
               fileInputStream.read(by);
               outputStream = response.getOutputStream();
               outputStream.write(by);
               jsonResult.setSuc(true);
               jsonResult.setMsg("文件下载成功！");
           }
       }catch (Exception e){

       }finally {
           try{
               if(outputStream!=null){
                   outputStream.close();
               }
               if(fileInputStream!=null){
                   fileInputStream.close();
               }

           }catch (Exception e){
               jsonResult.setMsg("文件下载异常！");
           }
       }
       return jsonResult;
    }
}
