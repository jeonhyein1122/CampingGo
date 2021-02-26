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

import de.hdodenhof.circleimageview.CircleImageView;

public class Shopping_DetailActivity extends AppCompatActivity {

    Context context;
    ArrayList<ShoppingRecyclerItem> items;

    ImageView detailIv;
    TextView detailTitle;
    TextView detailPrice;
    TextView detailDetail;

    CircleImageView profile;
    TextView nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping__detail);

        Intent intent=getIntent();

        String detailiv=intent.getStringExtra("shoppingiv");
        String detailtitle=intent.getStringExtra("title");
        String detailprice=intent.getStringExtra("price");
        String detaildetail=intent.getStringExtra("detail");

        detailIv=findViewById(R.id.shopping_detail_iv);
        detailTitle=findViewById(R.id.shopping_detail_title);
        detailPrice=findViewById(R.id.shopping_detail_price);
        detailDetail=findViewById(R.id.shopping_detail_detail);

        profile=findViewById(R.id.shopping_profile);
        nickname=findViewById(R.id.shopping_nickname);

        String imgUrl="http://jhyein1122.dothome.co.kr/Campinggo/"+detailiv;

        if (imgUrl==null) Glide.with(this).load(R.drawable.noimage).into(detailIv);
        else Glide.with(this).load(imgUrl).into(detailIv);
//        Glide.with(this).load(campingimgId).into(detailiv);
        detailTitle.setText(detailtitle);
        detailPrice.setText(detailprice+"원");
        detailDetail.setText(detaildetail);

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            detailIv.setTransitionName("shoppingimg");
        }

    }
}