package com.pay.alipay.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.FileItem;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayOfflineMaterialImageUploadResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;


/**
 * Created by yu on 2018/3/4.
 *
 * SDK包说明
 * alipay-sdk-java*.jar—————————支付宝SDK编译文件jar
 * alipay-sdk-java*-source.jar——————支付宝SDK源码文件jar
 * commons-logging-1.1.1.jar——————SDK依赖的日志jar
 * commons-logging-1.1.1-sources.jar———SDK依赖的日志源码jar
 */
@Component
public class PrivateKeySignature {

    //https://openauth.alipaydev.com/outh2/appToAppAuth.htm?app_id=2016091100484386&redirect_uri=http://127.0.0.1:8080/return.jsp
    private static String url = "https://openapi.alipay.com/gateway.do";
    private static String sand_url ="https://openapi.alipaydev.com/gateway.do";
    private static String APP_ID = "";
    private static String APP_PRIVATE_KEY="";
    private static String CHARSET = "utf-8";
    private static String ALIPAY_PUBLIC_KEY="";
    private static String RURL="";

    //sandBox
//    static{
//        Properties properties = new Properties();
//        try {
//            properties = PropertiesLoaderUtils.loadAllProperties("key.properties");
//            APP_ID = properties.getProperty("key.sand_appId");
//            APP_PRIVATE_KEY = properties.getProperty("key.sand_private");
//            ALIPAY_PUBLIC_KEY = properties.getProperty("key.sand_public");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //true
    static{
        Properties properties = new Properties();
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("key.properties");
            APP_ID = properties.getProperty("key.appId");
            APP_PRIVATE_KEY = properties.getProperty("key.private");
            ALIPAY_PUBLIC_KEY = properties.getProperty("key.public");
            RURL = properties.getProperty("key.url");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getAuthorizeURL(){
        //sandbox
        //String url = "https://openauth.alipaydev.com/oauth2/appToAppAuth.htm?app_id="+APP_ID+"&redirect_uri="+"http://127.0.0.1:8080/return.html";
        //实际环境
        //https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=2015101400446982&redirect_uri=http%3A%2F%2Fexample.com
        String url = "https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id="+APP_ID+"&redirect_uri="+RURL;
        return url;
    }


//    public static AlipayClient getClient_sand(){
//        AlipayClient alipayClient = new DefaultAlipayClient(sand_url,APP_ID,APP_PRIVATE_KEY,"json",CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
//        return alipayClient;
//    }

    public static AlipayClient getClient(){
        AlipayClient alipayClient = new DefaultAlipayClient(url,APP_ID,APP_PRIVATE_KEY,"json",CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        return alipayClient;
    }


}
