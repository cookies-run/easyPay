package com.pay.alipay.utils;

/**
 * Created by yu on 2018/6/16.
 */
public class AlipayConfig {

    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088xxxxxxxxxxxx";
    // 商户的私钥
    public static String private_key = "";
    // 支付宝的公钥(固定)
    public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    // 支付宝的公钥(页面)
//    public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    //支付宝网关
    public static String alipay_url = "https://openapi.alipay.com/gateway.do";
    // 支付宝分配给开发者的应用ID
    public static String app_id = "100000000000000";

    public static String content_type = "JSON";


    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";
    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";
    // 签名方式 不需修改
    public static String sign_type = "RSA";

}
