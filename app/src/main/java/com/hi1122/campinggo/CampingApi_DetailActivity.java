package com.hi1122.campinggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CampingApi_DetailActivity extends AppCompatActivity {

    Context context;
    ArrayList<CampingApiRecyclerItem> items;


    ImageView detailiv;
    TextView detailname;
    TextView detaillineintro;
    TextView detailintro;
    TextView detailinduty;
    TextView detailaddr1;
    TextView detailaddr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping_api__detail);

        Intent intent=getIntent();
        String nameId=intent.getStringExtra("nameId");
        String lineintroId=intent.getStringExtra("lineintroId");
        String campingimgId=intent.getStringExtra("campingimgId");
        String introId=intent.getStringExtra("introId");
        String indutyId=intent.getStringExtra("indutyId");
        String addr1Id=intent.getStringExtra("addr1Id");
        String addr2Id=intent.getStringExtra("addr2Id");

//        getSupportActionBar().setTitle(titleId+"");


        detailiv=findViewById(R.id.detailiv);
        detailname=findViewById(R.id.detailname);
        detaillineintro=findViewById(R.id.detaillineintro);
        detailintro=findViewById(R.id.detailintro);
        detailinduty=findViewById(R.id.detailinduty);
        detailaddr1=findViewById(R.id.detailaddr1);
        detailaddr2=findViewById(R.id.detailaddr2);



//        if(item.campingimgId!=null) Glide.with(context).load(item.campingimg).into(vh.campingimg); //load "http://www.naver.com"
//        else  Glide.with(context).load(R.drawable.noimage).into(vh.campingimg);


        Glide.with(this).load(campingimgId).into(detailiv);
        detailname.setText(nameId);
        detaillineintro.setText(lineintroId);
        detailintro.setText(introId);
        detailinduty.setText(indutyId);
        detailaddr1.setText(addr1Id);
        detailaddr2.setText(addr2Id);



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