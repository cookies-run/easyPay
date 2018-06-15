package com.pay.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.pay.alipay.entity.Authorize;
import com.pay.alipay.utils.PrivateKeySignature;
import com.pay.core.entity.JsonResult;
import com.pay.user.entity.User;
import com.pay.user.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static com.pay.common.shiro.ShiroUtils.getShiroUser;

/**
 * Created by yu on 2018/3/31.
 */
@Controller
@RequestMapping("/authorize")
public class AuthorizeController {

    @Autowired
    private LoginService loginService;

    //获取发起授权的url和二维码数据
    @RequestMapping("/lunchAuthorize")
    @ResponseBody
    public Authorize lunchAuthorize(@RequestParam("phone") String phone) {
        Authorize authorize = new Authorize();
        authorize.setUrl(PrivateKeySignature.getAuthorizeURL());
        return authorize;
    }

    //获取授权回调的值app_auth_code换取app_auth_token（应用授权令牌）
    @RequestMapping("/getAuthToken")
    @ResponseBody
    public JsonResult returnAuthCode(@RequestParam("app_auth_code") String app_auth_code){
        JsonResult jsonResult = null;
        //获取当前的用户id
        User user = getShiroUser();
        String phone = user.getPhone();
        //获取用户对应的app_auth_code，使用一次后失效，一天（从生成app_auth_code开始的24小时）未被使用自动过期
        AlipayClient alipayClient = PrivateKeySignature.getClient();
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        request.setBizContent("{" +
                "    \"grant_type\":\"authorization_code\"," +
                "    \"code\":\""+app_auth_code+"\"" +
                "}");
        try {
            AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);

            if(response.isSuccess()){
                HashMap<String,String> map = new HashMap<>();
                map.put(user.getPhone(),response.getAppAuthToken());
                //保存当前用户response.getAppAuthToken()
                int i = loginService.addAliAuthToken(phone,response.getAppAuthToken());

                if(i>0){
                    jsonResult = new JsonResult(map,"认证存储成功",true);
                }else{
                    jsonResult = new JsonResult(null,"认证存储失败",false);
                }
            }else {
                jsonResult = new JsonResult(null,"认证失败",false);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }




}
