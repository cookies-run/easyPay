package com.pay.school.entity;

import javax.persistence.*;

@Table(name = "school_info")
public class SchoolInfo {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 学校名称
     */
    @Column(name = "school_name")
    private String schoolName;

    /**
     * 学校的校徽或logo，在学校展示页面作为学校的标识。该字段为图片的链接地址，只支持png或jpg图片格式，图片高度为108，宽度为108 ，不大于20k。注意：此链接要求公网可以访问，否则无法正常展示。
     */
    @Column(name = "school_icon")
    private String schoolIcon;

    /**
     * 如果填写了school_icon参数，则此字段不能为空。目前只支持png和jpg两种格式
     */
    @Column(name = "school_icon_type")
    private String schoolIconType;

    /**
     * 学校(机构)标识码（由教育部按照国家标准及编码规则编制，可以在教育局官网查询）
     */
    @Column(name = "school_stdcode")
    private String schoolStdcode;

    /**
     * 学校的类型： 
1：代表托儿所； 2：代表幼儿园；3：代表小学；4：代表初中；5：代表高中。 
如果学校兼有多种属性，可以连写，比如： 
45：代表兼有初中部高中部；34：代表兼有小学部初中部
     */
    @Column(name = "school_type")
    private String schoolType;

    /**
     * 省份的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）	
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 省名称	
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 城市的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 区县的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     */
    @Column(name = "district_code")
    private String districtCode;

    /**
     * 区县名称
     */
    @Column(name = "district_name")
    private String districtName;

    /**
     * 商家名称，每个接入教育缴费的ISV商家名称，由ISV自己提供
     */
    @Column(name = "isv_name")
    private String isvName;

    /**
     * 此通知地址是为了保持教育缴费平台与ISV商户支付状态一致性。用户支付成功后，支付宝会根据本isv_notify_url，通过POST请求的形式将支付结果作为参数通知到商户系统，ISV商户可以根据返回的参数更新账单状态。
     */
    @Column(name = "isv_notify_url")
    private String isvNotifyUrl;

    /**
     * 填写已经签约教育缴费的isv的支付宝PID
     */
    @Column(name = "isv_pid")
    private String isvPid;

    /**
     * ISV联系电话,用于账单详情页面显示
     */
    @Column(name = "isv_phone")
    private String isvPhone;

    /**
     * 学校用来签约支付宝教育缴费的支付宝PID
     */
    @Column(name = "school_pid")
    private String schoolPid;

    /**
     * 学校信息维护完成支付宝返回值
     */
    @Column(name = "school_no")
    private String schoolNo;

    public SchoolInfo(Integer id, String schoolName, String schoolIcon, String schoolIconType, String schoolStdcode, String schoolType, String provinceCode, String provinceName, String cityCode, String cityName, String districtCode, String districtName, String isvName, String isvNotifyUrl, String isvPid, String isvPhone, String schoolPid, String schoolNo) {
        this.id = id;
        this.schoolName = schoolName;
        this.schoolIcon = schoolIcon;
        this.schoolIconType = schoolIconType;
        this.schoolStdcode = schoolStdcode;
        this.schoolType = schoolType;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.isvName = isvName;
        this.isvNotifyUrl = isvNotifyUrl;
        this.isvPid = isvPid;
        this.isvPhone = isvPhone;
        this.schoolPid = schoolPid;
        this.schoolNo = schoolNo;
    }

    public SchoolInfo() {
        super();
    }

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学校名称
     *
     * @return school_name - 学校名称
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * 设置学校名称
     *
     * @param schoolName 学校名称
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    /**
     * 获取学校的校徽或logo，在学校展示页面作为学校的标识。该字段为图片的链接地址，只支持png或jpg图片格式，图片高度为108，宽度为108 ，不大于20k。注意：此链接要求公网可以访问，否则无法正常展示。
     *
     * @return school_icon - 学校的校徽或logo，在学校展示页面作为学校的标识。该字段为图片的链接地址，只支持png或jpg图片格式，图片高度为108，宽度为108 ，不大于20k。注意：此链接要求公网可以访问，否则无法正常展示。
     */
    public String getSchoolIcon() {
        return schoolIcon;
    }

    /**
     * 设置学校的校徽或logo，在学校展示页面作为学校的标识。该字段为图片的链接地址，只支持png或jpg图片格式，图片高度为108，宽度为108 ，不大于20k。注意：此链接要求公网可以访问，否则无法正常展示。
     *
     * @param schoolIcon 学校的校徽或logo，在学校展示页面作为学校的标识。该字段为图片的链接地址，只支持png或jpg图片格式，图片高度为108，宽度为108 ，不大于20k。注意：此链接要求公网可以访问，否则无法正常展示。
     */
    public void setSchoolIcon(String schoolIcon) {
        this.schoolIcon = schoolIcon == null ? null : schoolIcon.trim();
    }

    /**
     * 获取如果填写了school_icon参数，则此字段不能为空。目前只支持png和jpg两种格式
     *
     * @return school_icon_type - 如果填写了school_icon参数，则此字段不能为空。目前只支持png和jpg两种格式
     */
    public String getSchoolIconType() {
        return schoolIconType;
    }

    /**
     * 设置如果填写了school_icon参数，则此字段不能为空。目前只支持png和jpg两种格式
     *
     * @param schoolIconType 如果填写了school_icon参数，则此字段不能为空。目前只支持png和jpg两种格式
     */
    public void setSchoolIconType(String schoolIconType) {
        this.schoolIconType = schoolIconType == null ? null : schoolIconType.trim();
    }

    /**
     * 获取学校(机构)标识码（由教育部按照国家标准及编码规则编制，可以在教育局官网查询）
     *
     * @return school_stdcode - 学校(机构)标识码（由教育部按照国家标准及编码规则编制，可以在教育局官网查询）
     */
    public String getSchoolStdcode() {
        return schoolStdcode;
    }

    /**
     * 设置学校(机构)标识码（由教育部按照国家标准及编码规则编制，可以在教育局官网查询）
     *
     * @param schoolStdcode 学校(机构)标识码（由教育部按照国家标准及编码规则编制，可以在教育局官网查询）
     */
    public void setSchoolStdcode(String schoolStdcode) {
        this.schoolStdcode = schoolStdcode == null ? null : schoolStdcode.trim();
    }

    /**
     * 获取学校的类型： 
1：代表托儿所； 2：代表幼儿园；3：代表小学；4：代表初中；5：代表高中。 
如果学校兼有多种属性，可以连写，比如： 
45：代表兼有初中部高中部；34：代表兼有小学部初中部
     *
     * @return school_type - 学校的类型： 
1：代表托儿所； 2：代表幼儿园；3：代表小学；4：代表初中；5：代表高中。 
如果学校兼有多种属性，可以连写，比如： 
45：代表兼有初中部高中部；34：代表兼有小学部初中部
     */
    public String getSchoolType() {
        return schoolType;
    }

    /**
     * 设置学校的类型： 
1：代表托儿所； 2：代表幼儿园；3：代表小学；4：代表初中；5：代表高中。 
如果学校兼有多种属性，可以连写，比如： 
45：代表兼有初中部高中部；34：代表兼有小学部初中部
     *
     * @param schoolType 学校的类型： 
1：代表托儿所； 2：代表幼儿园；3：代表小学；4：代表初中；5：代表高中。 
如果学校兼有多种属性，可以连写，比如： 
45：代表兼有初中部高中部；34：代表兼有小学部初中部
     */
    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType == null ? null : schoolType.trim();
    }

    /**
     * 获取省份的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）	
     *
     * @return province_code - 省份的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）	
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 设置省份的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）	
     *
     * @param provinceCode 省份的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）	
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * 获取省名称	
     *
     * @return province_name - 省名称	
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省名称	
     *
     * @param provinceName 省名称	
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 获取城市的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     *
     * @return city_code - 城市的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置城市的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     *
     * @param cityCode 城市的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 获取城市名称
     *
     * @return city_name - 城市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置城市名称
     *
     * @param cityName 城市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 获取区县的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     *
     * @return district_code - 区县的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * 设置区县的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     *
     * @param districtCode 区县的国家编码（国家统计局出版的行政区划代码 http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/）
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    /**
     * 获取区县名称
     *
     * @return district_name - 区县名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置区县名称
     *
     * @param districtName 区县名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    /**
     * 获取商家名称，每个接入教育缴费的ISV商家名称，由ISV自己提供
     *
     * @return isv_name - 商家名称，每个接入教育缴费的ISV商家名称，由ISV自己提供
     */
    public String getIsvName() {
        return isvName;
    }

    /**
     * 设置商家名称，每个接入教育缴费的ISV商家名称，由ISV自己提供
     *
     * @param isvName 商家名称，每个接入教育缴费的ISV商家名称，由ISV自己提供
     */
    public void setIsvName(String isvName) {
        this.isvName = isvName == null ? null : isvName.trim();
    }

    /**
     * 获取此通知地址是为了保持教育缴费平台与ISV商户支付状态一致性。用户支付成功后，支付宝会根据本isv_notify_url，通过POST请求的形式将支付结果作为参数通知到商户系统，ISV商户可以根据返回的参数更新账单状态。
     *
     * @return isv_notify_url - 此通知地址是为了保持教育缴费平台与ISV商户支付状态一致性。用户支付成功后，支付宝会根据本isv_notify_url，通过POST请求的形式将支付结果作为参数通知到商户系统，ISV商户可以根据返回的参数更新账单状态。
     */
    public String getIsvNotifyUrl() {
        return isvNotifyUrl;
    }

    /**
     * 设置此通知地址是为了保持教育缴费平台与ISV商户支付状态一致性。用户支付成功后，支付宝会根据本isv_notify_url，通过POST请求的形式将支付结果作为参数通知到商户系统，ISV商户可以根据返回的参数更新账单状态。
     *
     * @param isvNotifyUrl 此通知地址是为了保持教育缴费平台与ISV商户支付状态一致性。用户支付成功后，支付宝会根据本isv_notify_url，通过POST请求的形式将支付结果作为参数通知到商户系统，ISV商户可以根据返回的参数更新账单状态。
     */
    public void setIsvNotifyUrl(String isvNotifyUrl) {
        this.isvNotifyUrl = isvNotifyUrl == null ? null : isvNotifyUrl.trim();
    }

    /**
     * 获取填写已经签约教育缴费的isv的支付宝PID
     *
     * @return isv_pid - 填写已经签约教育缴费的isv的支付宝PID
     */
    public String getIsvPid() {
        return isvPid;
    }

    /**
     * 设置填写已经签约教育缴费的isv的支付宝PID
     *
     * @param isvPid 填写已经签约教育缴费的isv的支付宝PID
     */
    public void setIsvPid(String isvPid) {
        this.isvPid = isvPid == null ? null : isvPid.trim();
    }

    /**
     * 获取ISV联系电话,用于账单详情页面显示
     *
     * @return isv_phone - ISV联系电话,用于账单详情页面显示
     */
    public String getIsvPhone() {
        return isvPhone;
    }

    /**
     * 设置ISV联系电话,用于账单详情页面显示
     *
     * @param isvPhone ISV联系电话,用于账单详情页面显示
     */
    public void setIsvPhone(String isvPhone) {
        this.isvPhone = isvPhone == null ? null : isvPhone.trim();
    }

    /**
     * 获取学校用来签约支付宝教育缴费的支付宝PID
     *
     * @return school_pid - 学校用来签约支付宝教育缴费的支付宝PID
     */
    public String getSchoolPid() {
        return schoolPid;
    }

    /**
     * 设置学校用来签约支付宝教育缴费的支付宝PID
     *
     * @param schoolPid 学校用来签约支付宝教育缴费的支付宝PID
     */
    public void setSchoolPid(String schoolPid) {
        this.schoolPid = schoolPid == null ? null : schoolPid.trim();
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
}