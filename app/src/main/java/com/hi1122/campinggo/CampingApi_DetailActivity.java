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
    TextView detailtitle;
    TextView detailsubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping_api__detail);

        Intent intent=getIntent();
        String titleId=intent.getStringExtra("titleId");
        String subtitleId=intent.getStringExtra("subtitleId");
        int campingimgId=intent.getIntExtra("campingimgId",R.drawable.adv);

//        getSupportActionBar().setTitle(titleId+"");

        detailiv=findViewById(R.id.detailiv);
        detailtitle=findViewById(R.id.detailtitle);
        detailsubtitle=findViewById(R.id.detailsubtitle);

        Glide.with(this).load(campingimgId).into(detailiv);
        detailtitle.setText(titleId);
        detailsubtitle.setText(subtitleId);


        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            detailiv.setTransitionName("campingimg");
        }

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.layout.activity_camping_a_p_i){
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}