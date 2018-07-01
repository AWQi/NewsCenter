package com.example.live;

import java.util.List;

public class JoyResult<T>{

    // 响应业务状态
    public int status;
    // 响应消息
    public String msg;
    public JoyResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    public int getStatus() {
        return status;
    }
    public String getMsg() {
        return msg;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    static  public class  JoyObj<T>{
        public T data;
        // 响应业务状态
        public int status;
        // 响应消息
        public String msg;

        public JoyObj(T data, int status, String msg) {
            this.data = data;
            this.status = status;
            this.msg = msg;
        }
        public Object getData() {
            return data;
        }
        public int getStatus() {
            return status;
        }
        public String getMsg() {
            return msg;
        }
        public void setData(T data) {
            this.data = data;
        }
        public void setStatus(int status) {
            this.status = status;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }



    static public  class  JoyList <T> {
        // 响应业务状态
        private int status;
        // 响应消息
        private String msg;
        private  List <T> data = null;
        public int getStatus() {
            return status;
        }
        public String getMsg() {
            return msg;
        }
        public void setStatus(int status) {
            this.status = status;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public void setData(List data) {
            this.data = data;
        }
        public List getData() {
            return data;
        }
    }


}

