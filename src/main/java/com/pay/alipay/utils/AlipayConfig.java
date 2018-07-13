package com.pay.alipay.utils;

/**
 * Created by yu on 2018/6/16.
 */
public class AlipayConfig {

    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String partner = "2088221439451601";

    //商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
    public static String private_key = "";

    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    //public static String seller_id = "2088221439451601" ;

    // 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String alipay_public_key  = "";

    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    // 签名方式 不需修改
    public static String sign_type = "RSA";

    // 支付类型 ，无需修改
    public static String payment_type = "1";

    // 调用的接口名，无需修改
    public static String service = "create_direct_pay_by_user";

    public static String notify_url = "http://k12pay.com/notify/payNotify" ;



}
