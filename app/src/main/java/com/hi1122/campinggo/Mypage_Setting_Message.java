package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Mypage_Setting_Message extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_setting_message);

        tv=findViewById(R.id.tv);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String message=intent.getStringExtra("msg");

        getSupportActionBar().setTitle(name);
        tv.setText(message);
    }
}