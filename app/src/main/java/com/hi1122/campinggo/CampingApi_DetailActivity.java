package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CampingApi_DetailActivity extends AppCompatActivity {

    ImageView detailiv;
    TextView detailname;
    TextView detaillineintro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping_api__detail);

        Intent intent=getIntent();
        String nameId=intent.getStringExtra("nameId");
        String lineintroId=intent.getStringExtra("lineintroId");
        String campingimgId=intent.getStringExtra("campingimgId");

//        getSupportActionBar().setTitle(titleId+"");

        detailiv=findViewById(R.id.detailiv);
        detailname=findViewById(R.id.detailname);
        detaillineintro=findViewById(R.id.detaillineintro);

        Glide.with(this).load(campingimgId).into(detailiv);
        detailname.setText(nameId);
        detaillineintro.setText(lineintroId);


        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            detailiv.setTransitionName("campingimg");
        }

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.layout.activity_camping_a_p_i_main){
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}