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
        vh.title.setText(item.title);
        vh.subtitle.setText(item.subtitle);

        Glide.with(context).load(item.campingimg).into(vh.campingimg); //load "http://www.naver.com"

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView campingimg;
        TextView title;
        TextView subtitle;


        public VH(@NonNull View itemView) {
            super(itemView);

            campingimg=itemView.findViewById(R.id.campingimg);
            title=itemView.findViewById(R.id.campingtitle);
            subtitle=itemView.findViewById(R.id.campingsubtitle);

            //아이템뷰 클릭
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();
//                    Toast.makeText(context, "클릭됨", Toast.LENGTH_SHORT).show();

                    String titleId=items.get(position).title;
                    String subtitleId=items.get(position).subtitle;
                    int campingimgId=items.get(position).campingimg;

                    Intent intent=new Intent(context,CampingApi_DetailActivity.class);
                    intent.putExtra("titleId",titleId);
                    intent.putExtra("subtitleId",subtitleId);
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
