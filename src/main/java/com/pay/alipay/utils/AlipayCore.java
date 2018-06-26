package com.pay.alipay.utils;

import java.util.*;

/**
 * Created by yu on 2018/6/17.
 */
public class AlipayCore {

    public static Map<String,String> paraFilter(Map<String,String> sArray){
        Map<String,String> result = new HashMap<String,String>();
        if(sArray==null||sArray.size()<=0){
            return result;
        }

        for (String key:sArray.keySet()){
            String value = sArray.get(key);
            if(value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")){
                continue;
            }
            result.put(key,value);
        }
        return result;
    }

    public static String createLinkString(Map<String,String> params){

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";

        for(int i=0;i<keys.size();i++){
            String key = keys.get(i);
            String value = params.get(key);

            if(i == keys.size()-1){
                prestr = prestr+key+"="+value;
            }else {
                prestr = prestr+key+"="+value+"&";
            }
        }
        return prestr;
    }


}
