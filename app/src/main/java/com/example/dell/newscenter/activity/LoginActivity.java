package com.example.dell.newscenter.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.utils.ApplicationUtil;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {
    // UI references.
    private AutoCompleteTextView telET;
    private EditText pwdTV;
    private View mProgressView;
    private View mLoginFormView;
    private String tel ;
    private String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        telET = findViewById(R.id.telET);
        pwdTV =  findViewById(R.id.pwdTV);
        //  登录
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();

            }
        });
//        loginBtn.performClick();


        Button registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     *
     * 登录
     *
     */
    private void attemptLogin() {
        //
        tel = telET.getText().toString();
        pwd = pwdTV.getText().toString();
        // 发送请求验证信息  返回 user 的json 字符

        //  解析  user 到 bean

        User user  = new User(1,"AWQI","18734741443","http://img2.woyaogexing.com/2018/05/20/4c21bd94d67c19b9!400x400_big.jpg",1);
        if (user.getId()!=0){  // 若正确  则获取到id不为0
            //  存储到ApplicationUtil .user
            ApplicationUtil.setUser(user);
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();// 关闭登录界面
        }else {//  不正确  报错
            Toast.makeText(LoginActivity.this,"账号或密码不正确",Toast.LENGTH_LONG).show();
        }
    }

}

