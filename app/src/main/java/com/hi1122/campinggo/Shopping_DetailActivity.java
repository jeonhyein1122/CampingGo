package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Shopping_DetailActivity extends AppCompatActivity {

    Context context;
    ArrayList<ShoppingRecyclerItem> items;
    TextView dialogtv;

    ImageView detailIv;
    TextView detailTitle;
    TextView detailPrice;
    TextView detailDetail;

    CircleImageView userprofile;
    TextView nickname;
    String detailnickname;
    String detailprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_detail);

        Intent intent=getIntent();

        String detailiv=intent.getStringExtra("shoppingiv");
        String detailtitle=intent.getStringExtra("title");
        String detailprice=intent.getStringExtra("price");
        String detaildetail=intent.getStringExtra("detail");
        detailnickname=intent.getStringExtra("nickname");
        detailprofile=intent.getStringExtra("profile");

//        Log.i("nic",detailnickname);
        detailIv=findViewById(R.id.shopping_detail_iv);
        detailTitle=findViewById(R.id.shopping_detail_title);
        detailPrice=findViewById(R.id.shopping_detail_price);
        detailDetail=findViewById(R.id.shopping_detail_detail);

        userprofile=findViewById(R.id.shopping_profile);
        nickname=findViewById(R.id.shopping_nickname);

        String imgUrl="http://jhyein1122.dothome.co.kr/Campinggo/"+detailiv;



        if (imgUrl==null) Glide.with(this).load(R.drawable.noimage).into(detailIv);
        else Glide.with(this).load(imgUrl).into(detailIv);
//        Glide.with(this).load(campingimgId).into(detailiv);
        detailTitle.setText(detailtitle);
        detailPrice.setText(detailprice+"원");
        detailDetail.setText(detaildetail);

        nickname.setText(detailnickname);



        Glide.with(this).load(detailprofile).into(userprofile);

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            detailIv.setTransitionName("shoppingimg");
        }

    }

    AlertDialog dialog;
    public void clickchatting(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        LayoutInflater inflater=getLayoutInflater();
        LinearLayout layout=(LinearLayout)inflater.inflate(R.layout.dialogchatting,null);

        dialogtv=layout.findViewById(R.id.dialogchat);

        builder.setView(layout);
        if (G.nickname!=null) {

            builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Shopping_DetailActivity.this, Chatting_Activity.class);
                    intent.putExtra("detailnickname",detailnickname);
                    intent.putExtra("profile",detailprofile);
                    startActivity(intent);


                }
            });
            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }else Toast.makeText(Shopping_DetailActivity.this, "로그인이 필요한 서비스 입니다.", Toast.LENGTH_SHORT).show();
       
    }
}