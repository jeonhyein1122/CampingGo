package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CampingApi_DetailActivity extends AppCompatActivity {

    Context context;
    ArrayList<CampingApi_RecyclerItem> items;


    ImageView detailiv;
    TextView detailname;
    TextView detaillineintro;
    TextView detailintro;
    TextView detailinduty;
    TextView detailaddr1;
    TextView detailaddr2;
    TextView detailresveCl;
    TextView detailmodifiedtime;
    TextView detailcreatedtime;
    TextView detailtel;
    TextView detailhomepage;
    TextView detailresveUrl;
    TextView dlctCl;

    TextView detailmax;
    TextView detailmay;

    String mapx;
    String mapy;
    String nameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camping_api_detail);

        Intent intent=getIntent();
        nameId=intent.getStringExtra("nameId");
        String lineintroId=intent.getStringExtra("lineintroId");
        String campingimgId=intent.getStringExtra("campingimgId");
        String introId=intent.getStringExtra("introId");
        String indutyId=intent.getStringExtra("indutyId");
        String addr1Id=intent.getStringExtra("addr1Id");
        String addr2Id=intent.getStringExtra("addr2Id");
        String resveClId=intent.getStringExtra("resveClId");
        String detailmodifiedtimeId=intent.getStringExtra("modifiedtimeId");
        String detailcreatedtimeId=intent.getStringExtra("createdtimeId");
        String detailtelId=intent.getStringExtra("telId");
        String detailhomepageId=intent.getStringExtra("homepageId");
        String detailresveUrlId=intent.getStringExtra("resveUrlId");
        mapx=intent.getStringExtra("mapX");
        mapy=intent.getStringExtra("mapY");
        String detailcontenId=intent.getStringExtra("contentID");
        String lctCl=intent.getStringExtra("lctCl");


//        getSupportActionBar().setTitle(titleId+"");



        detailiv=findViewById(R.id.detailiv);
        detailname=findViewById(R.id.detailname);
        detaillineintro=findViewById(R.id.detaillineintro);
        detailintro=findViewById(R.id.detailintro);
        detailinduty=findViewById(R.id.detailinduty);
        detailaddr1=findViewById(R.id.detailaddr1);
        detailaddr2=findViewById(R.id.detailaddr2);

        detailresveCl=findViewById(R.id.detailresveCl);
        detailmodifiedtime=findViewById(R.id.detailmodifiedtime);
        detailcreatedtime=findViewById(R.id.detailcreatedtime);
        detailtel=findViewById(R.id.detailtel);
        detailhomepage=findViewById(R.id.detailhompage);
        detailresveUrl=findViewById(R.id.detailresveUrl);
        dlctCl=findViewById(R.id.lctCl);


        if (campingimgId==null) Glide.with(this).load(R.drawable.noimage).into(detailiv);
        else Glide.with(this).load(campingimgId).into(detailiv);
//        Glide.with(this).load(campingimgId).into(detailiv);
        detailname.setText(nameId);
        detaillineintro.setText(lineintroId);
        detailintro.setText(introId);
        detailinduty.setText(indutyId);
        detailaddr1.setText("주소 : "+addr1Id);
        detailaddr2.setText(addr2Id);

        if (resveClId==null) detailresveCl.setText(resveClId);
        else detailresveCl.setText("예약방법 : "+resveClId);

        detailmodifiedtime.setText("수정일 : "+detailmodifiedtimeId);
        detailcreatedtime.setText("등록일 : "+detailcreatedtimeId);
        detailtel.setText("☎ "+detailtelId);
//        Glide.with(this).load(detailhomepageId).into(detailhomepage);
        detailhomepage.setText("홈페이지 주소 : "+detailhomepageId);
        detailresveUrl.setText("예약 사이트 : "+detailresveUrlId);
        dlctCl.setText("입지:"+lctCl);




        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            detailiv.setTransitionName("campingimg");
        }

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.layout.camping_api_main){
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void detailmap(View view) {

        Intent intent=new Intent(CampingApi_DetailActivity.this,CampingApi_Detailmap.class);
        intent.putExtra("mapx",mapx);
        intent.putExtra("mapy",mapy);
        intent.putExtra("nameId",nameId);

        startActivity(intent);


    }
}