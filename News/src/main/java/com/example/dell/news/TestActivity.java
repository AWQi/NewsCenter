package com.example.dell.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        LinearLayout test1 = findViewById(R.id.testactivitylayout);
//        Log.d(TAG, "test1: "+test1.toString());
//        LinearLayout test2 = findViewById(R.id.testactivitylayout);
//        Log.d(TAG, "test2: "+test2.toString());

    }
}
