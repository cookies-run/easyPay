package com.pay.school.service;

import com.pay.core.entity.JsonResult;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by cy on 2018/6/18.
 */
public interface BillManageService {
    JsonResult billDelete(Integer billId);
    JsonResult billExport(Integer billId);
    JsonResult downLoadBill(HttpServletResponse response, String fileName, String filePath);
}
