package com.pay.bill.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.pay.alipay.utils.PrivateKeySignature;
import com.pay.bill.service.BillService;
import com.pay.core.entity.JsonResult;
import com.pay.school.dao.SchoolDao;
import com.pay.school.entity.StudentBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yu on 2018/6/15.
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private SchoolDao schoolDao;


    @Override
    public JsonResult closeBill(Integer id) {
        //获取账单
        List<StudentBill> studentBills = schoolDao.getStudentBillById(id);
        AlipayClient alipayClient = PrivateKeySignature.getClient();

        //循环关闭账单
        for(StudentBill bean : studentBills){
            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
            request.setBizContent("{" +
                    "\"trade_no\":\""+bean.getOrderNo()+"\"," +
                    "\"out_trade_no\":\""+bean.getOutTradeNo()+"\"," +
                    "  }");
            try {
                AlipayTradeCloseResponse response = alipayClient.execute(request);
                if(response.isSuccess()){
                    System.out.println("账单关闭成功");
                } else {
                    System.out.println("账单关闭失败");
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
