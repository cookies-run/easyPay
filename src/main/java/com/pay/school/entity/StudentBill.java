package com.pay.school.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "student_bill")
public class StudentBill {
    @Id
    private Integer id;

    /**
     * 账单主键
     */
    @Column(name = "bill_id")
    private Integer billId;

    /**
     * 序号
     */
    private Integer order;

    /**
     * 学生的身份证号，如果ISV有学生身份证号，则同步身份证号作为学生唯一标识。此字段与student_code、家长user_mobile至少选一个。 
大陆身份证必须是18位 ， 其它地区或国家的身份证开头需要加"IC"开头区分并且不超过18位，但查询账单的时候不要带"IC"
     */
    @Column(name = "student_identify")
    private String studentIdentify;

    /**
     * 学校编码，录入学校接口返回的参数
     */
    @Column(name = "school_no")
    private String schoolNo;

    /**
     * 孩子名字
     */
    @Column(name = "child_name")
    private String childName;

    /**
     * 孩子所在年级
     */
    private String grade;

    /**
     * 孩子所在班级
     */
    @Column(name = "class_in")
    private String classIn;

    /**
     * 学生的学号，只支持字母和数字类型，一般以教育局学号为准，作为学生的唯一标识。此字段与student_identify、家长user_mobile至少选一个
     */
    @Column(name = "student_code")
    private String studentCode;

    /**
     * 缴费项模式：空或"N"，表示缴费项不可选， 
"M"表示缴费项为可选 ，支持单选和多选。
     */
    @Column(name = "charge_type")
    private String chargeType;

    /**
     * 总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]， 
如果是非多选项，是要和缴费项的总和相同，多选模式不做验证
     */
    private BigDecimal amount;

    /**
     * 缴费截止时间，格式"yyyy-MM-dd HH:mm:ss"，日期要大于当前时间。请注意，过期时间不宜设置过短。
     */
    @Column(name = "gmt_end")
    private String gmtEnd;

    /**
     * 截止日期是否生效，与gmt_end发布配合使用,N为gmt_end不生效，用户过期后仍可以缴费；Y为gmt_end生效，用户过期后，不能再缴费。
     */
    @Column(name = "end_enable")
    private String endEnable;

    /**
     * 缴费详情：输入json格式字符串。Json定义：key填写缴费项名称，value填写缴费项金额，金额保留2位小数
     */
    @Column(name = "charge_item")
    private String chargeItem;

    /**
     * ISV端的缴费账单编号
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 缴费账单名称
     */
    @Column(name = "charge_bill_title")
    private String chargeBillTitle;

    /**
     * 学校账号
     */
    private String phone;

    private Date time;

    /**
     * 支付宝返回的账单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * Isv支付宝pid, 支付宝签约后，返回给ISV编号
     */
    @Column(name = "partner_id")
    private String partnerId;

    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 孩子的家长信息，最多一次输入10个家长，此字段做为识别家长的孩子用，与student_identify、student_code至少选一个
     */
    private String users;

    public StudentBill(Integer id, Integer billId, Integer order, String studentIdentify, String schoolNo, String childName, String grade, String classIn, String studentCode, String chargeType, BigDecimal amount, String gmtEnd, String endEnable, String chargeItem, String outTradeNo, String chargeBillTitle, String phone, Date time, String orderNo, String partnerId, String users) {
        this.id = id;
        this.billId = billId;
        this.order = order;
        this.studentIdentify = studentIdentify;
        this.schoolNo = schoolNo;
        this.childName = childName;
        this.grade = grade;
        this.classIn = classIn;
        this.studentCode = studentCode;
        this.chargeType = chargeType;
        this.amount = amount;
        this.gmtEnd = gmtEnd;
        this.endEnable = endEnable;
        this.chargeItem = chargeItem;
        this.outTradeNo = outTradeNo;
        this.chargeBillTitle = chargeBillTitle;
        this.phone = phone;
        this.time = time;
        this.orderNo = orderNo;
        this.partnerId = partnerId;
        this.users = users;
    }

    public StudentBill() {
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
     * 获取账单主键
     *
     * @return bill_id - 账单主键
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * 设置账单主键
     *
     * @param billId 账单主键
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * 获取序号
     *
     * @return order - 序号
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 设置序号
     *
     * @param order 序号
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 获取学生的身份证号，如果ISV有学生身份证号，则同步身份证号作为学生唯一标识。此字段与student_code、家长user_mobile至少选一个。 
大陆身份证必须是18位 ， 其它地区或国家的身份证开头需要加"IC"开头区分并且不超过18位，但查询账单的时候不要带"IC"
     *
     * @return student_identify - 学生的身份证号，如果ISV有学生身份证号，则同步身份证号作为学生唯一标识。此字段与student_code、家长user_mobile至少选一个。 
大陆身份证必须是18位 ， 其它地区或国家的身份证开头需要加"IC"开头区分并且不超过18位，但查询账单的时候不要带"IC"
     */
    public String getStudentIdentify() {
        return studentIdentify;
    }

    /**
     * 设置学生的身份证号，如果ISV有学生身份证号，则同步身份证号作为学生唯一标识。此字段与student_code、家长user_mobile至少选一个。 
大陆身份证必须是18位 ， 其它地区或国家的身份证开头需要加"IC"开头区分并且不超过18位，但查询账单的时候不要带"IC"
     *
     * @param studentIdentify 学生的身份证号，如果ISV有学生身份证号，则同步身份证号作为学生唯一标识。此字段与student_code、家长user_mobile至少选一个。 
大陆身份证必须是18位 ， 其它地区或国家的身份证开头需要加"IC"开头区分并且不超过18位，但查询账单的时候不要带"IC"
     */
    public void setStudentIdentify(String studentIdentify) {
        this.studentIdentify = studentIdentify == null ? null : studentIdentify.trim();
    }

    /**
     * 获取学校编码，录入学校接口返回的参数
     *
     * @return school_no - 学校编码，录入学校接口返回的参数
     */
    public String getSchoolNo() {
        return schoolNo;
    }

    /**
     * 设置学校编码，录入学校接口返回的参数
     *
     * @param schoolNo 学校编码，录入学校接口返回的参数
     */
    public void setSchoolNo(String schoolNo) {
        this.schoolNo = schoolNo == null ? null : schoolNo.trim();
    }

    /**
     * 获取孩子名字
     *
     * @return child_name - 孩子名字
     */
    public String getChildName() {
        return childName;
    }

    /**
     * 设置孩子名字
     *
     * @param childName 孩子名字
     */
    public void setChildName(String childName) {
        this.childName = childName == null ? null : childName.trim();
    }

    /**
     * 获取孩子所在年级
     *
     * @return grade - 孩子所在年级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置孩子所在年级
     *
     * @param grade 孩子所在年级
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * 获取孩子所在班级
     *
     * @return class_in - 孩子所在班级
     */
    public String getClassIn() {
        return classIn;
    }

    /**
     * 设置孩子所在班级
     *
     * @param classIn 孩子所在班级
     */
    public void setClassIn(String classIn) {
        this.classIn = classIn == null ? null : classIn.trim();
    }

    /**
     * 获取学生的学号，只支持字母和数字类型，一般以教育局学号为准，作为学生的唯一标识。此字段与student_identify、家长user_mobile至少选一个
     *
     * @return student_code - 学生的学号，只支持字母和数字类型，一般以教育局学号为准，作为学生的唯一标识。此字段与student_identify、家长user_mobile至少选一个
     */
    public String getStudentCode() {
        return studentCode;
    }

    /**
     * 设置学生的学号，只支持字母和数字类型，一般以教育局学号为准，作为学生的唯一标识。此字段与student_identify、家长user_mobile至少选一个
     *
     * @param studentCode 学生的学号，只支持字母和数字类型，一般以教育局学号为准，作为学生的唯一标识。此字段与student_identify、家长user_mobile至少选一个
     */
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    /**
     * 获取缴费项模式：空或"N"，表示缴费项不可选， 
"M"表示缴费项为可选 ，支持单选和多选。
     *
     * @return charge_type - 缴费项模式：空或"N"，表示缴费项不可选， 
"M"表示缴费项为可选 ，支持单选和多选。
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * 设置缴费项模式：空或"N"，表示缴费项不可选， 
"M"表示缴费项为可选 ，支持单选和多选。
     *
     * @param chargeType 缴费项模式：空或"N"，表示缴费项不可选， 
"M"表示缴费项为可选 ，支持单选和多选。
     */
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType == null ? null : chargeType.trim();
    }

    /**
     * 获取总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]， 
如果是非多选项，是要和缴费项的总和相同，多选模式不做验证
     *
     * @return amount - 总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]， 
如果是非多选项，是要和缴费项的总和相同，多选模式不做验证
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]， 
如果是非多选项，是要和缴费项的总和相同，多选模式不做验证
     *
     * @param amount 总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]， 
如果是非多选项，是要和缴费项的总和相同，多选模式不做验证
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取缴费截止时间，格式"yyyy-MM-dd HH:mm:ss"，日期要大于当前时间。请注意，过期时间不宜设置过短。
     *
     * @return gmt_end - 缴费截止时间，格式"yyyy-MM-dd HH:mm:ss"，日期要大于当前时间。请注意，过期时间不宜设置过短。
     */
    public String getGmtEnd() {
        return gmtEnd;
    }

    /**
     * 设置缴费截止时间，格式"yyyy-MM-dd HH:mm:ss"，日期要大于当前时间。请注意，过期时间不宜设置过短。
     *
     * @param gmtEnd 缴费截止时间，格式"yyyy-MM-dd HH:mm:ss"，日期要大于当前时间。请注意，过期时间不宜设置过短。
     */
    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd == null ? null : gmtEnd.trim();
    }

    /**
     * 获取截止日期是否生效，与gmt_end发布配合使用,N为gmt_end不生效，用户过期后仍可以缴费；Y为gmt_end生效，用户过期后，不能再缴费。
     *
     * @return end_enable - 截止日期是否生效，与gmt_end发布配合使用,N为gmt_end不生效，用户过期后仍可以缴费；Y为gmt_end生效，用户过期后，不能再缴费。
     */
    public String getEndEnable() {
        return endEnable;
    }

    /**
     * 设置截止日期是否生效，与gmt_end发布配合使用,N为gmt_end不生效，用户过期后仍可以缴费；Y为gmt_end生效，用户过期后，不能再缴费。
     *
     * @param endEnable 截止日期是否生效，与gmt_end发布配合使用,N为gmt_end不生效，用户过期后仍可以缴费；Y为gmt_end生效，用户过期后，不能再缴费。
     */
    public void setEndEnable(String endEnable) {
        this.endEnable = endEnable == null ? null : endEnable.trim();
    }

    /**
     * 获取缴费详情：输入json格式字符串。Json定义：key填写缴费项名称，value填写缴费项金额，金额保留2位小数
     *
     * @return charge_item - 缴费详情：输入json格式字符串。Json定义：key填写缴费项名称，value填写缴费项金额，金额保留2位小数
     */
    public String getChargeItem() {
        return chargeItem;
    }

    /**
     * 设置缴费详情：输入json格式字符串。Json定义：key填写缴费项名称，value填写缴费项金额，金额保留2位小数
     *
     * @param chargeItem 缴费详情：输入json格式字符串。Json定义：key填写缴费项名称，value填写缴费项金额，金额保留2位小数
     */
    public void setChargeItem(String chargeItem) {
        this.chargeItem = chargeItem == null ? null : chargeItem.trim();
    }

    /**
     * 获取ISV端的缴费账单编号
     *
     * @return out_trade_no - ISV端的缴费账单编号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置ISV端的缴费账单编号
     *
     * @param outTradeNo ISV端的缴费账单编号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     * 获取缴费账单名称
     *
     * @return charge_bill_title - 缴费账单名称
     */
    public String getChargeBillTitle() {
        return chargeBillTitle;
    }

    /**
     * 设置缴费账单名称
     *
     * @param chargeBillTitle 缴费账单名称
     */
    public void setChargeBillTitle(String chargeBillTitle) {
        this.chargeBillTitle = chargeBillTitle == null ? null : chargeBillTitle.trim();
    }

    /**
     * 获取学校账号
     *
     * @return phone - 学校账号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置学校账号
     *
     * @param phone 学校账号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取支付宝返回的账单号
     *
     * @return order_no - 支付宝返回的账单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置支付宝返回的账单号
     *
     * @param orderNo 支付宝返回的账单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取Isv支付宝pid, 支付宝签约后，返回给ISV编号
     *
     * @return partner_id - Isv支付宝pid, 支付宝签约后，返回给ISV编号
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * 设置Isv支付宝pid, 支付宝签约后，返回给ISV编号
     *
     * @param partnerId Isv支付宝pid, 支付宝签约后，返回给ISV编号
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    /**
     * 获取孩子的家长信息，最多一次输入10个家长，此字段做为识别家长的孩子用，与student_identify、student_code至少选一个
     *
     * @return users - 孩子的家长信息，最多一次输入10个家长，此字段做为识别家长的孩子用，与student_identify、student_code至少选一个
     */
    public String getUsers() {
        return users;
    }

    /**
     * 设置孩子的家长信息，最多一次输入10个家长，此字段做为识别家长的孩子用，与student_identify、student_code至少选一个
     *
     * @param users 孩子的家长信息，最多一次输入10个家长，此字段做为识别家长的孩子用，与student_identify、student_code至少选一个
     */
    public void setUsers(String users) {
        this.users = users == null ? null : users.trim();
    }
}