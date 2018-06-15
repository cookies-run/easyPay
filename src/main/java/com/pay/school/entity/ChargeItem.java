package com.pay.school.entity;

import java.math.BigDecimal;

/**
 * Created by yu on 2018/4/16.
 */
public class ChargeItem {

    private String item_name;//缴费项名称
    private String item_price;//缴费项金额
    private String item_serial_number;//缴费项序号
    private String item_maximum;//缴费项最大可选数
    private String item_mandatory;//缴费项是否必选

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_serial_number() {
        return item_serial_number;
    }

    public void setItem_serial_number(String item_serial_number) {
        this.item_serial_number = item_serial_number;
    }

    public String getItem_maximum() {
        return item_maximum;
    }

    public void setItem_maximum(String item_maximum) {
        this.item_maximum = item_maximum;
    }

    public String getItem_mandatory() {
        return item_mandatory;
    }

    public void setItem_mandatory(String item_mandatory) {
        this.item_mandatory = item_mandatory;
    }
}
