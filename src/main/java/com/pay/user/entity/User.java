package com.pay.user.entity;

import javax.persistence.*;

public class User {
    @Id
    private String phone;

    private String password;

    /**
     * 用户角色
     */
    private String role;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 学校信息维护完成支付宝返回值
     */
    @Column(name = "school_no")
    private String schoolNo;

    /**
     * 商户授权令牌
     */
    @Column(name = "appAuthToken")
    private String appauthtoken;

    public User(String phone, String password, String role, String email, String schoolNo, String appauthtoken) {
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.email = email;
        this.schoolNo = schoolNo;
        this.appauthtoken = appauthtoken;
    }

    public User() {
        super();
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户角色
     *
     * @return role - 用户角色
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置用户角色
     *
     * @param role 用户角色
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * 获取用户邮箱
     *
     * @return email - 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户邮箱
     *
     * @param email 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取学校信息维护完成支付宝返回值
     *
     * @return school_no - 学校信息维护完成支付宝返回值
     */
    public String getSchoolNo() {
        return schoolNo;
    }

    /**
     * 设置学校信息维护完成支付宝返回值
     *
     * @param schoolNo 学校信息维护完成支付宝返回值
     */
    public void setSchoolNo(String schoolNo) {
        this.schoolNo = schoolNo == null ? null : schoolNo.trim();
    }

    /**
     * 获取商户授权令牌
     *
     * @return appAuthToken - 商户授权令牌
     */
    public String getAppauthtoken() {
        return appauthtoken;
    }

    /**
     * 设置商户授权令牌
     *
     * @param appauthtoken 商户授权令牌
     */
    public void setAppauthtoken(String appauthtoken) {
        this.appauthtoken = appauthtoken == null ? null : appauthtoken.trim();
    }
}