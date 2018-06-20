package com.example.dell.newscenter.myview.mainactivity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.newscenter.R;

public class PublicationDynamicsActivity extends AppCompatActivity {
private Button addPhotoBtn = null;
private Button addVideoBtn = null;
private EditText addTitleET = null;
private EditText addDescribeET = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_dynamics);


        addPhotoBtn = findViewById(R.id.addPhotoBtn);
        addVideoBtn = findViewById(R.id.addVideoBtn);
        addTitleET = findViewById(R.id.addTitleET);
        addDescribeET = findViewById(R.id.addDescribeET);

        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  AlertDialog.Builder(PublicationDynamicsActivity.this)
                        .setTitle("请选择" )
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setSingleChoiceItems(new  String[] {"拍照", "相册选取" },  0 ,
                                new  DialogInterface.OnClickListener() {
                                    public   void  onClick(DialogInterface dialog,  int  which) {
                                        dialog.dismiss();
                                    }
                                }
                        )
                        .setNegativeButton("取消" ,  null )
                        .show();
            }
        });

    }
}
