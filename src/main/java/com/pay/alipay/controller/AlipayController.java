package com.pay.alipay.controller;

import com.pay.alipay.utils.AlipayNotify;
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

    @RequestMapping(value = "/getPayNotify")
    @ResponseBody
    public String getPayNotify(HttpServletRequest request)throws Exception{
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator();iter.hasNext();){
            String name = (String) iter.next();
            String[] values = (String[])requestParams.get(name);
            String valueStr = "";
            for (int i = 0;i<values.length;i++){
                valueStr = (i==values.length-1)?valueStr+values[i]:valueStr+values[i]+",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //获取支付宝的通知返回参数
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

        //异步通知ID
        String notify_id=request.getParameter("notify_id");

        //sign
        String sign=request.getParameter("sign");

        if (notify_id!=""&&notify_id!=null){
            if (AlipayNotify.verifyResponse(notify_id).equals("true")){
                if(AlipayNotify.getSignVeryfy(params,sign)){
                    if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){
                        //业务
                        //更新单据为已完成
                       boolean flag = billService.saveBillTradeNo(trade_no, out_trade_no, trade_status);
                       if(flag){
                           return "success";
                       }else{
                           return "operate error";
                       }
                    }
                    return "success";
                }else{
                    return "sign fail" ;
                }
            }else {
                return "response fail" ;
            }
        }else {
            return "no notify message";
        }
    }

    @RequestMapping(value = "/getPay")
    @ResponseBody
    public String getPay(){
        return null;
    }

}
