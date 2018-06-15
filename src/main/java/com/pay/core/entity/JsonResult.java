package com.pay.core.entity;

import java.io.Serializable;

/**
 * Created by yu on 2018/4/8.
 */
public class JsonResult<T> implements Serializable {

    private T data;
    private String msg;
    private boolean suc;

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuc() {
        return suc;
    }

    public void setSuc(boolean suc) {
        this.suc = suc;
    }

    public JsonResult(){
        super();
    }

    public JsonResult(T data,String msg,boolean suc){
        this.data=data;
        this.msg=msg;
        this.suc = suc;
    }

    public JsonResult(T data,String msg){
        this.data=data;
        this.msg=msg;
        this.suc = true;
    }

    public JsonResult(T data){
        this.data=data;
        this.suc = true;
    }
}
