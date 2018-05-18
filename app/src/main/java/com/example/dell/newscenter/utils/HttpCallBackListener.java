package com.example.dell.newscenter.utils;

public interface HttpCallBackListener {
     void onfinish(String response);
     void onEror(Exception e);
}
