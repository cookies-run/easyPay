package com.pay.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay.core.entity.JsonResult;

import com.pay.school.entity.Bill;
import com.pay.school.entity.ChargeItem;
import com.pay.school.entity.StudentBill;
import com.pay.school.entity.Users;
import com.pay.school.service.SchoolService;
import com.pay.user.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.pay.common.shiro.ShiroUtils.getShiroUser;

/**
 * Created by yu on 2018/4/14.
 */
@Controller
@RequestMapping("/import")
public class ImportBill {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "/importBill")
    @ResponseBody
    public JsonResult importBill(MultipartFile[] files){
        JsonResult jsonResult = null;
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = sdf.format(curDate);
        User user = getShiroUser();
        String phone = user.getPhone();
        String role = user.getRole();
        String childUser = null;
        if("childUser".equals(role)){
            phone = user.getParentAccount();
            childUser = user.getPhone();
        }
        try {
            for(MultipartFile file : files){
                InputStream in = file.getInputStream();
                //excel
                Workbook workbook = null;
                workbook = new XSSFWorkbook(in);
                Sheet sheet = workbook.getSheetAt(0);
                //对Sheet中的每一行进行迭代
                List<StudentBill> studentBills = new ArrayList<>();
                String billName = "";

                billName = sheet.getRow(0).getCell(0).getStringCellValue();
                for(Row r : sheet){
                    //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
                    if(r.getRowNum()<2){
                        continue;
                    }
                    //随机数生成账单
                    UUID uuid = UUID.randomUUID();
                    String preNo = uuid.toString();
                    //创建entity，保存
                    StudentBill studentBill = new StudentBill();
                    studentBill.setOrder(Integer.valueOf(getCellValue(r.getCell(0))));
                    studentBill.setChildName(getCellValue(r.getCell(1)));
                    studentBill.setClassIn(getCellValue(r.getCell(2)));
                    studentBill.setStudentCode(getCellValue(r.getCell(3)));
                    studentBill.setStudentIdentify(getCellValue(r.getCell(4)));
                    studentBill.setChargeType(getCellValue(r.getCell(6)));

                    //缴费项 json
                    List<ChargeItem> chargeItemList = new ArrayList<>();
                    double amount=0.0d;
                    if(r.getLastCellNum()>11){
                        for(int i=8;i<r.getLastCellNum();i+=5){
                            ChargeItem chargeItem = new ChargeItem();
                            chargeItem.setItem_serial_number(getCellValue(r.getCell(i)));
                            chargeItem.setItem_name(getCellValue(r.getCell(i+1)));
                            chargeItem.setItem_price(getCellValue(r.getCell(i+2)));
                            chargeItem.setItem_maximum(getCellValue(r.getCell(i+3)));
                            chargeItem.setItem_mandatory(getCellValue(r.getCell(i+4)));
                            chargeItemList.add(chargeItem);
                            amount += Double.valueOf(getCellValue(r.getCell(i+2)))*Double.valueOf(getCellValue(r.getCell(i+3)));
                        }
                    }
                    //补充信息>>>>
                    //家长信息
                    List<Users> users = new ArrayList<>();
                    Users users1 = new Users();
                    users1.setUser_mobile(getCellValue(r.getCell(5)));
                    users.add(users1);
                    //补充信息<<<<
                    //list转json
                    ObjectMapper mapper = new ObjectMapper();
                    studentBill.setChargeItem(mapper.writeValueAsString(chargeItemList));
                    studentBill.setAmount(new BigDecimal(amount));
                    studentBill.setGmtEnd(r.getCell(7).getStringCellValue());
                    studentBill.setEndEnable("N");
                    studentBill.setPhone(phone);

                    studentBill.setUsers(mapper.writeValueAsString(users));

                    String billNo = preNo.replace("-","");
                    studentBill.setOutTradeNo(billNo);
                    studentBill.setChargeBillTitle(billName);
                    studentBills.add(studentBill);

                }
                //保存到数据库
                Bill bill = new Bill();
                bill.setBillName(billName);
                bill.setImportTime(curDate);
                bill.setPhone(phone);
                bill.setChildUser(childUser);

                jsonResult = schoolService.addStudentBill(bill,studentBills);

            }



        } catch (IOException e) {
            jsonResult = new JsonResult(null,e.getMessage(),false);
            e.printStackTrace();
        }
        return jsonResult;
    }


    private String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC://数字
                    value = cell.getNumericCellValue() + "";
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) {
                            value = sdf.format(date);
                        } else {
                            value = "";
                        }
                    } else {
                        value = new DecimalFormat("#.##").format(cell.getNumericCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING://字符串
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_FORMULA://公式
                    value = cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN://boolean
                    value = cell.getBooleanCellValue() + "";
                case HSSFCell.CELL_TYPE_BLANK://空值
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR://错误
                    value = "非法字符";
                    break;
                default:
                    value = "未知类型";
                    break;
            }
        }
        return value.trim();
    }
}
