package com.example.dell.newscenter.myview.InfoActivity.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.activity.MainActivity;
import com.example.dell.newscenter.bean.Project;
import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.myview.InfoActivity.userinfo.UserInfoActivity;
import com.example.dell.newscenter.myview.videoplayactivity.VideoPlayActivity;
import com.example.dell.newscenter.utils.ApplicationUtil;
import com.example.dell.newscenter.utils.JsonUtil;
import com.google.zxing.WriterException;
import com.qrcode.activity.QrCodeMainActivity;
import com.qrcode.zxing.activity.CaptureActivity;
import com.qrcode.zxing.encoding.EncodingHandler;

public class QrCodeActivity extends AppCompatActivity {
    private static final String TAG = "QrCodeActivity";
    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;
private ImageView myQrCode = null;
private Button scanQrCode = null;
private String qrStr = null;
private Bitmap qrBitmap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        getData();

        scanQrCode = findViewById(R.id.scanQrCode);
        myQrCode = findViewById(R.id.myQrCode);


        myQrCode.setImageBitmap(qrBitmap);
        scanQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QrCodeActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    public void getData(){
        User user  = ApplicationUtil.getUser();
        String userInfo = JsonUtil.ObjToStr(user);
        QrBean qrBean = new QrBean(QrBean.USER,userInfo);
        qrStr = JsonUtil.ObjToStr(qrBean);
        try {
            qrBitmap = EncodingHandler.createQRCode(qrStr, 800);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     *          获取扫描结果
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
             QrBean scanQrBean = JsonUtil.StrToObj(scanResult,QrBean.class);
             if (scanQrBean.kind==QrBean.USER){
                 Intent intent = new Intent(QrCodeActivity.this, UserInfoActivity.class);
                 intent.putExtra("user",(User)JsonUtil.StrToObj(scanQrBean.content,User.class));
                 intent.putExtra("isEditAble",false);
                 startActivity(intent);
                 this.finish();
             }else if(scanQrBean.kind==QrBean.PROJECT){
                 Intent intent = new Intent(QrCodeActivity.this, VideoPlayActivity.class);
                 intent.putExtra("live_item",(Project)JsonUtil.StrToObj(scanQrBean.content,Project.class));
                 startActivity(intent);
                 this.finish();
            }else{
                 Log.d(TAG, "二维码解析出错: ");
            }
        }
    }
}
