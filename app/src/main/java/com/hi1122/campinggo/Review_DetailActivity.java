package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Review_DetailActivity extends AppCompatActivity {

    Context context;
    ArrayList<ReviewRecyclerItem> items;

    ImageView detailIv;
    TextView detailTitle;
    TextView detailLoaddate;
    TextView detailDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review__detail);

        Intent intent=getIntent();

        String detailiv=intent.getStringExtra("reviewiv");
        String detailtitle=intent.getStringExtra("title");
        String detailloaddate=intent.getStringExtra("loaddate");
        String detaildetail=intent.getStringExtra("detail");

        detailIv=findViewById(R.id.review_detail_iv);
        detailTitle=findViewById(R.id.review_detail_title);
        detailLoaddate=findViewById(R.id.review_detail_loaddate);
        detailDetail=findViewById(R.id.review_detail_detail);


        if (detailiv==null) Glide.with(this).load(R.drawable.noimage).into(detailIv);
        else Glide.with(this).load(detailiv).into(detailIv);
//        Glide.with(this).load(campingimgId).into(detailiv);
        detailTitle.setText(detailtitle);
        detailLoaddate.setText("등록일"+detailloaddate);
        detailDetail.setText(detaildetail);

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            detailIv.setTransitionName("reviewimg");
        }

    }
}