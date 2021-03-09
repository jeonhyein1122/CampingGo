package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Second_Detail extends AppCompatActivity {

    Context context;
    ArrayList<ShoppingRecyclerItem> items;

    ImageView detailIv;
    TextView detailTitle;
    TextView detailSubtitle;
    TextView detailDetail;

    CircleImageView userprofile;
    TextView nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_second__detail);

        Intent intent=getIntent();

        String detailiv=intent.getStringExtra("tipiv");
        String detailtitle=intent.getStringExtra("title");
        String detailsubtitle=intent.getStringExtra("subtitle");
        String detaildetail=intent.getStringExtra("detail");
        String detailnickname=intent.getStringExtra("nickname");
//        String detailprofile=intent.getStringExtra("profile");

        detailIv=findViewById(R.id.tip_detail_iv);
        detailTitle=findViewById(R.id.tip_detail_title);
        detailSubtitle=findViewById(R.id.tip_detail_subtitle);
        detailDetail=findViewById(R.id.tip_detail_detail);

//        userprofile=findViewById(R.id.shopping_profile);
        nickname=findViewById(R.id.tip_nickname);

        String imgUrl="http://jhyein1122.dothome.co.kr/Campinggotip/"+detailiv;

        if (imgUrl==null) Glide.with(this).load(R.drawable.noimage).into(detailIv);
        else Glide.with(this).load(imgUrl).into(detailIv);
//        Glide.with(this).load(campingimgId).into(detailiv);
        detailTitle.setText(detailtitle);
        detailSubtitle.setText("#"+detailsubtitle);
        detailDetail.setText(detaildetail);


        nickname.setText(detailnickname);

//        Glide.with(this).load(detailprofile).into(userprofile);

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            detailIv.setTransitionName("tipimg");
        }

    }
}