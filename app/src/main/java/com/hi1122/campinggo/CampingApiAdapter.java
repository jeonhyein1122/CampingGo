package com.hi1122.campinggo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CampingApiAdapter extends RecyclerView.Adapter{
    Context context;
    ArrayList<CampingApiRecyclerItem> items;

    public CampingApiAdapter(Context context, ArrayList<CampingApiRecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);

        View itemView=inflater.inflate(R.layout.campingapi_recycler_item,parent,false);
        VH holder=new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        CampingApiRecyclerItem item=items.get(position);
        vh.name.setText(item.name+"");
        if (item.lineintro !=null) vh.lineintro.setText(item.lineintro+"");
        else vh.lineintro.setText("설명이 없습니다");

        if(item.campingimg!=null) Glide.with(context).load(item.campingimg).into(vh.campingimg); //load "http://www.naver.com"
        else  Glide.with(context).load(R.drawable.noimage).into(vh.campingimg); //load "http://www.naver.com"

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView campingimg;
        TextView name;
        TextView lineintro;


        public VH(@NonNull View itemView) {
            super(itemView);

            campingimg=itemView.findViewById(R.id.campingimg);
            name=itemView.findViewById(R.id.name);
            lineintro=itemView.findViewById(R.id.lineintro);

            //아이템뷰 클릭
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();
//                    Toast.makeText(context, "클릭됨", Toast.LENGTH_SHORT).show();

                    String nameId=items.get(position).name;
                    String lineintroId=items.get(position).lineintro;
                    String campingimgId=items.get(position).campingimg;

                    Intent intent=new Intent(context,CampingApi_DetailActivity.class);
                    intent.putExtra("nameId",nameId);
                    intent.putExtra("lineintroId",lineintroId);
                    intent.putExtra("campingimgId",campingimgId);


                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity)context,new Pair<View,String>(campingimg,"Campingimg"));
                        context.startActivity(intent,options.toBundle());
                    }else {
                        context.startActivity(intent);
                    }

                }

            });

        }
    }
}
