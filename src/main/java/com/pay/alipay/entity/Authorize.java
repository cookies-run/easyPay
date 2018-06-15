package com.pay.alipay.entity;

/**
 * Created by yu on 2018/3/31.
 */
public class Authorize {

    private String url;
    private String picCode;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }
}
