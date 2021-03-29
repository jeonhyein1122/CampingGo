package com.hi1122.campinggo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

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
    String detailyoutubeurl;
    String detailiv;
    YouTubePlayerFragment youTubeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_second__detail);


            FragmentManager fragmentManager = getFragmentManager();
            youTubeFragment = (YouTubePlayerFragment) fragmentManager.findFragmentById(R.id.youtubeFragment);

            youTubeFragment.initialize("first", new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.cueVideo( detailyoutubeurl ); //로딩이 완료되어 자동실행 되지는 않음
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });


        Intent intent=getIntent();

//        detailiv=intent.getStringExtra("tipiv");
        String detailtitle=intent.getStringExtra("title");
        String detailsubtitle=intent.getStringExtra("subtitle");
        String detailsubtitle2=intent.getStringExtra("subtitle2");
        String detailsubtitle3=intent.getStringExtra("subtitle3");
        String detaildetail=intent.getStringExtra("detail");
        String detailnickname=intent.getStringExtra("nickname");
        detailyoutubeurl=intent.getStringExtra("youtubeurl");


        detailIv=findViewById(R.id.tip_detail_iv);
        detailTitle=findViewById(R.id.tip_detail_title);
        detailSubtitle=findViewById(R.id.tip_detail_subtitle);
        detailDetail=findViewById(R.id.tip_detail_detail);

//        userprofile=findViewById(R.id.shopping_profile);

        String imgUrl="http://jhyein1122.dothome.co.kr/Campinggotip/"+detailiv;

//        if (imgUrl==null) Glide.with(this).load(R.drawable.noimage).into(detailIv);
//        else Glide.with(this).load(imgUrl).into(detailIv);
//        Glide.with(this).load(campingimgId).into(detailiv);


        detailTitle.setText(detailtitle);
        detailSubtitle.setText("#"+detailsubtitle+"#"+detailsubtitle2+"#"+detailsubtitle3);
        detailDetail.setText(detaildetail);


//        nickname.setText(detailnickname);

//        Glide.with(this).load(detailprofile).into(userprofile);

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            detailIv.setTransitionName("tipimg");
        }

    }
}