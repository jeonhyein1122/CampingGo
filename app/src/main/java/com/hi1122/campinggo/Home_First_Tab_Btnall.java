package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_First_Tab_Btnall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__first__tab__btnall);
    }

    public void Seoul(View view) {
        Intent intent=new Intent(this, CampingApi_Main.class);
        startActivity(intent);

        finish();
    }
}