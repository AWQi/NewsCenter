package com.example.dell.newscenter.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.RegisterUser;
import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.utils.JoyHttpUtil;
import com.example.dell.newscenter.utils.JoyResult;
import com.example.dell.newscenter.utils.SnackbarUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
private EditText registerTelET;
private Button registerTelBtn;
private EditText registorNameET;
private EditText registerPwdET;
private EditText registerRePwdET;
private EditText verificationCodeET;
private RadioGroup registerGenderRG;
private Button  registerCommitBtn;

 private  String tel = null;
 private  String name = null;
 private  String pwd = null;
 private  String rePwd = null;
 private  String getVerificationCode = null;
 private  String verificationCode = null;
 private  int gender;
private Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerTelET = findViewById(R.id.registerTelET);
        registerTelBtn = findViewById(R.id.registerTelBtn);
        registorNameET = findViewById(R.id.registorNameET);
        registerPwdET = findViewById(R.id.registerPwdET);
        registerRePwdET = findViewById(R.id.registerRePwdET);
        verificationCodeET = findViewById(R.id.verificationCodeET);
        registerGenderRG = findViewById(R.id.registerGenderRG);
        registerCommitBtn = findViewById(R.id.registerCommitBtn);

        registerTelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel = registerTelET.getText().toString();
                boolean isPhone  = isPhone(tel);  //   验证手机号  格式
                if (isPhone){
                    //  生成验证码
//                verificationCode = new String(String.valueOf((Math.random()*9+1)*100000));
                    // 发送验证码短信
                    JoyHttpUtil.preRegister(registerTelET.getText().toString(), new JoyHttpUtil.JoyObjCallBack(JoyHttpUtil.OBJECT_TTYPE) {
                        @Override
                        public void analyticData(JoyResult.JoyObj joyObj) {
                            final int status = joyObj.getStatus();
                            final  String msg   = joyObj.getMsg();
                            // 重新发送倒计时
                            activity.runOnUiThread(
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                      Toast toast = Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT);
                                        //    toast .setGravity(Gravity.CENTER,0,0);
                                            toast.show();

                                            Log.d(TAG, "run: ---------------------------"+msg);
                                            if (status==200){
                                               new Thread(new Runnable() {
                                                   @Override
                                                   public void run() {
                                                       registerTelBtn.setClickable(false);
                                                       int second = 60;
                                                       while (second>0){
                                                           try {
                                                               registerTelBtn.setText(second+"秒");
                                                               Thread.sleep(1000);
                                                               second--;
                                                           } catch (InterruptedException e) {
                                                               e.printStackTrace();
                                                           }
                                                       }
                                                       registerTelBtn.setClickable(true);
                                                       registerTelBtn.setText("重新发送");
                                                   }
                                               }).start();
                                            }
                                        }
                                    })
                            );
                        }
                    });
                }else {
                    //   手机号  格式不正确
                    Toast.makeText(RegisterActivity.this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
                }

            }
        });

        registerCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isRegular = checkInf();
                if (isRegular==null){
                    gender = getGender();

                    //  封装信息到 Bean
                    RegisterUser registerUser = new RegisterUser(name,pwd,tel,gender);
                    // 发送信息到 服务端
                    JoyHttpUtil.register(registerUser,verificationCode, new JoyHttpUtil.JoyObjCallBack(JoyHttpUtil.OBJECT_TTYPE) {
                        @Override
                        public void analyticData(final JoyResult.JoyObj joyObj) {
                            activity.runOnUiThread(new Runnable() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void run() {
                                    int status =joyObj.getStatus();
                                    String data = (String) joyObj.data;
                                    if (status==200){
                                        new AlertDialog.Builder(RegisterActivity.this)
                                                .setTitle("成功")  //  ;
                                                .setMessage("注册成功，请点击返回")
                                                .setIcon(R.drawable.logo)  //  图标
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                              Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                                    }
                                                })
                                                .create()
                                                .show();



                                        // 若成功  跳转界面
                                    }else {  // 若不成功  提示报错
                            Toast.makeText(RegisterActivity.this,data,Toast.LENGTH_SHORT).show();
                          }
                                }
                            });
                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this,isRegular,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private String checkInf(){
        tel = registerTelET.getText().toString();
        boolean isPhone  = isPhone(tel);  //   验证手机号  格式
        name = registorNameET.getText().toString();
        pwd = registerPwdET.getText().toString();
        rePwd = registerRePwdET.getText().toString();
        getVerificationCode = verificationCodeET.getText().toString();
        if (!isPhone){
            return  "手机号格式不正确";
        }if (name==null||name.length()>10||name.length()==0) {
            return "请输入1~10位范围内昵称";
        }
        if (pwd==null||pwd.length()>16||pwd.length()<6){
            return "请输入6~16位范围内密码";
        }
        if (rePwd==null||!rePwd.equals(pwd)){
            return "两次密码输入不一致";
        }
//        if (!getVerificationCode.equals(verificationCode)){
//            return  "验证码输入错误";
//        }
        return  null;
    }

    /**
     *
     *      检测手机号1  格式
     * @param inputText
     * @return
     */
    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }
    public  int getGender(){
       int id =  registerGenderRG.getCheckedRadioButtonId();
       switch (id){
           case R.id.registerGenderMan :
               return  0;
           case  R.id.registerGenderWoMan:
               return  1;
           case R.id.regiterGenderSecrecy:
                return  2;
           default: return  -1;
       }
    }
}
