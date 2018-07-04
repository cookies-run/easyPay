package com.pay.alipay.utils;

/**
 * Created by yu on 2018/6/16.
 */
public class AlipayConfig {

    // 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String partner = "2088221439451601";

    //商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
    public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOCSKCOPn1bVaTJT" +
            "daX0FGuAHJBmIsP7WY3h5RrJN9PJ96YqA7qAN0I2BLY+BMVN1xU7KsD5aFSsFlmW" +
            "L1g9e96tUMAUS/h1rTAE3KVWXxn3b5MFS3GSG3KC9HtSnPXcNZMXw/2gtsxemFoz" +
            "m2Nn2eobasqJYG1wq7+SuBNiQf/3AgMBAAECgYEA21MjTIfKcL4A/ZuxPSECiHMY" +
            "cpatwYZL0lwaq2x1z6xi57AE8DKkxU+IjoxHkIMTWrLscIelM3zWXyxklOOHgv/Q" +
            "PxnFGQLmC0Egd3aoUUWtSaGQinoLO+ufaUIvjhD7c9IGHSbwdCpcTNzuk5OwQW2p" +
            "4L3kqDkwgeCVFEpo3kECQQD+NHUjJ1diG7VWsMnLDIBInnI3AgkqNcEP/kFoSPD0" +
            "+r+VQJ/FgtjJ2DOxdkQF+8zCpAb9IslAAB1S3LJpKnMRAkEA4iggwwjcVbvd276B" +
            "Z3iMjl6lSTXlwn92f8mMAVjHF0Rn9kVDhL2ZIVn+v0cLnXZ0tPh2Kfr7r8BpXMdF" +
            "8AYyhwJAV5VTVo1TeiX50KiQnjjsahjNWU6GQdjLb+s2NewLuRw7rfzaD38JmmG3" +
            "TcllnnonDljdX5IArOBdOhN4FOvfQQJBAIYukOGnDDPB92w9p8GggKSn+yFo3R19" +
            "Su2ew3a5GcbwS06O4fSMGaL0JnWOzZFriuskz9cwlsw8OUdW5GP5CVkCQB0ULpxR" +
            "GuKNWL7N8X0/JmeuseVRSkUCAkOyqF3qGiefqKrKFwtr201YVPtGVDUKMW0FZPCg" +
            "BYktf8SS9o/1iqA=";

    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    //public static String seller_id = "2088221439451601" ;

    // 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

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
