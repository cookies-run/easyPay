package com.pay.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayEcoEduKtBillingModifyRequest;
import com.alipay.api.response.AlipayEcoEduKtBillingModifyResponse;
import com.pay.alipay.utils.AlipayConfig;
import com.pay.alipay.utils.AlipayNotify;
import com.pay.alipay.utils.PrivateKeySignature;
import com.pay.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yu on 2018/6/17.
 */
@Controller
@RequestMapping("/notify")
public class AlipayController {

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/payNotify")
    @ResponseBody
    public String getPayNotify(HttpServletRequest request)throws Exception{
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            params.put(name, valueStr);
        }
        //获取支付宝的通知返回参数
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

        String sign = new String(request.getParameter("sign").getBytes("ISO-8859-1"),"UTF-8");


        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,"GBK");
        if(flag){
            if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){
                //1:缴费成功，2:关闭账单，3、退费
                AlipayClient alipayClient = PrivateKeySignature.getClient();
                AlipayEcoEduKtBillingModifyRequest req = new AlipayEcoEduKtBillingModifyRequest();
                req.setBizContent("{" +
                        "\"trade_no\":\""+trade_no+"\"," +
                        "\"out_trade_no\":\""+out_trade_no+"\"," +
                        "\"status\":\"1\"" +
                        "  }");
                try {
                    AlipayEcoEduKtBillingModifyResponse response = alipayClient.execute(req);
                    if(response.isSuccess()){
                        //存储账单状态
                        billService.saveBillTradeNo(trade_no, out_trade_no, trade_status);
                        System.out.println("账单关闭成功");
                    } else {

                    }
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }


            }
            return "success";
        }else {
            return "response fail" ;
        }

    }

    @RequestMapping(value = "/getPay")
    @ResponseBody
    public String getPay(){
        return null;
    }

}
