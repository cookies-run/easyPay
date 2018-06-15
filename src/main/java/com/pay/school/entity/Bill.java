package com.pay.school.entity;

import java.util.Date;
import javax.persistence.*;

public class Bill {
    @Id
    private Integer id;

    /**
     * 账单名称
     */
    @Column(name = "bill_name")
    private String billName;

    /**
     * 账单上传时间
     */
    @Column(name = "import_time")
    private Date importTime;

    /**
     * 学校的手机号
     */
    private String phone;

    /**
     * 0-未发送，1-已发送
     */
    private Short state;

    public Bill(Integer id, String billName, Date importTime, String phone, Short state) {
        this.id = id;
        this.billName = billName;
        this.importTime = importTime;
        this.phone = phone;
        this.state = state;
    }

    public Bill() {
        super();
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账单名称
     *
     * @return bill_name - 账单名称
     */
    public String getBillName() {
        return billName;
    }

    /**
     * 设置账单名称
     *
     * @param billName 账单名称
     */
    public void setBillName(String billName) {
        this.billName = billName == null ? null : billName.trim();
    }

    /**
     * 获取账单上传时间
     *
     * @return import_time - 账单上传时间
     */
    public Date getImportTime() {
        return importTime;
    }

    /**
     * 设置账单上传时间
     *
     * @param importTime 账单上传时间
     */
    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    /**
     * 获取学校的手机号
     *
     * @return phone - 学校的手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置学校的手机号
     *
     * @param phone 学校的手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取0-未发送，1-已发送
     *
     * @return state - 0-未发送，1-已发送
     */
    public Short getState() {
        return state;
    }

    /**
     * 设置0-未发送，1-已发送
     *
     * @param state 0-未发送，1-已发送
     */
    public void setState(Short state) {
        this.state = state;
    }
}