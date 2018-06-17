package com.pay.bill.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayEcoEduKtBillingModifyRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.response.AlipayEcoEduKtBillingModifyResponse;
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
            AlipayEcoEduKtBillingModifyRequest request = new AlipayEcoEduKtBillingModifyRequest();
            request.setBizContent("{" +
                    "\"trade_no\":\""+bean.getOrderNo()+"\"," +
                    "\"out_trade_no\":\""+bean.getOutTradeNo()+"\"," +
                    "\"status\":\"1\"" +
//                    "\"status\":\"2\"," +
//                    "\"fund_change\":\"Y\"," +
//                    "\"refund_amount\":200.12," +
//                    "\"refund_reason\":\"正常退款\"," +
//                    "\"out_request_no\":\"HZ01RF001\"," +
//                    "\"buyer_logon_id\":\"159****5620\"," +
//                    "\"gmt_refund\":\"2015-11-27 15:45:57\"," +
//                    "\"buyer_user_id\":\"2088101117955611\"," +
//                    "\"refund_detail_item_list\":{" +
//                    "\"fund_channel\":\"ALIPAYACCOUNT\"," +
//                    "\"amount\":12.00," +
//                    "\"real_amount\":12.00" +
//                    "}" +
                    "  }");

            try {
                AlipayEcoEduKtBillingModifyResponse response = alipayClient.execute(request);
                if(response.isSuccess()){
                    System.out.println("账单关闭成功");
                } else {
                    System.out.println("账单关闭失败"+response.getMsg());
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
