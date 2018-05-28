package com.example.dell.newscenter.myview.InfoActivity.qrcode;

public class QrBean {
    static  public  final  int USER =1;
    static  public  final  int PROJECT  = 2;
    public  int kind ;  //代表数据种类，根据种类分辨 二维码内是user还是project
    public  String content;//  具体的数据

    public QrBean(int kind, String content) {
        this.kind = kind;
        this.content = content;
    }
}
