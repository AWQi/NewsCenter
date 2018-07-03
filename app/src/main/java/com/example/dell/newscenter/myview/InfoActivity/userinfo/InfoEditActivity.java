package com.example.dell.newscenter.myview.InfoActivity.userinfo;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.newscenter.R;
import com.example.dell.newscenter.bean.User;
import com.example.dell.newscenter.myview.base.CircleImageView;
import com.example.dell.newscenter.utils.ApplicationUtil;

public class InfoEditActivity extends AppCompatActivity {
private CircleImageView infoEditHeadCV = null;
private TextView infoEditNameTV = null;
private CircleImageView infoEditGenderCV = null;
private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_edit);

        user = ApplicationUtil.getUser();
        infoEditHeadCV = findViewById(R.id.infoEditHeadCV);
        infoEditNameTV = findViewById(R.id.infoEditNameTV);
        infoEditGenderCV = findViewById(R.id.infoEditGenderCV);


        Glide.with(InfoEditActivity.this)
                .load(user.getHeadUrl())
                .fitCenter()
                .into(infoEditHeadCV);
        infoEditNameTV.setText(user.getName());
        Glide.with(InfoEditActivity.this)
                .load(ApplicationUtil.genderId[user.getGender()])
                .fitCenter()
                .into(infoEditGenderCV);



    }
}
