package com.example.dell.newscenter.utils;

import java.util.List;

public class JoyResult<T>{

    // 响应业务状态
    private Integer status;
    // 响应消息
    private String msg;
    // 响应中的数据
    private String  data;
    public JoyResult() {
    }
    public JoyResult(Integer status, String msg, String data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
    public <T>T getDataObj(Class objClass){
        return (T)JsonUtil.StrToObj(getData(),objClass);
    }
    public List<T> getDataList(){
        return JsonUtil.StrToList(getData());
    }
}

