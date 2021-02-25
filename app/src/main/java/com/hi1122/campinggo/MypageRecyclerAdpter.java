package com.hi1122.campinggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MypageRecyclerAdpter extends RecyclerView.Adapter {

    Context context;
    ArrayList<MypageRecyclerItem> items;
    CircleImageView profile;
    TextView nickname;

    public MypageRecyclerAdpter(Context context, ArrayList<MypageRecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.mypage_recycler_item,parent,false);
        VH vh=new VH(itemView);
        return vh;

//        profile=itemView.findViewById(R.id.mypage_profile);
//        nickname=itemView.findViewById(R.id.mypage_nickname);
//        if (G.nickname !=null) nickname.setText(G.nickname);
//        if (G.profile != null) Glide.with().load(G.profile).into(profile);


    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;
        //값 연결작업..
        MypageRecyclerItem item=items.get(position);
        //나머지는 알아서서
    }


   @Override
    public int getItemCount() {
        return items.size();
    }

    class  VH extends RecyclerView.ViewHolder{

//        item.xml 안에 있는거 집어넣기
        ImageView iv;
        TextView title;
        TextView msg;
        RatingBar ratingBar; //별점


        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }

}
