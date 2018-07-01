package com.example.live;

import java.util.List;

public class StudioResult {
    // 响应业务状态
    private int status;
    // 响应消息
    private String msg;
    //
    private Object data;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public  static  class  startResult extends StudioResult{
        // 响应业务状态
        public int status;
        // 响应消息
        public String msg;
        //
        public  String data;
    }
    public static  class  stopResult extends StudioResult{
        // 响应业务状态
        public int status;
        // 响应消息
        public String msg;
        //
        public  int data;
    }
    public static  class  registerResult extends StudioResult{
        // 响应业务状态
        public int status;
        // 响应消息
        public String msg;
        //
        public int data;
    }
}
