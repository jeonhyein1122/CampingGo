package com.hi1122.campinggo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        View itemView=inflater.inflate(R.layout.activity_campingapi_recycler_item,parent,false);
        VH holder=new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        CampingApiRecyclerItem item=items.get(position);
        vh.name.setText(item.name+"");
        if (item.lineintro !=null) vh.lineintro.setText(item.lineintro+"");
        else vh.lineintro.setText(" ");

        if(item.campingimg!=null){
//            vh.campingimg.setVisibility(View.VISIBLE);
//            vh.itemView.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.campingimg).into(vh.campingimg);
        }
        else  Glide.with(context).load(R.drawable.noimage).into(vh.campingimg);
//       else vh.campingimg.setVisibility(View.GONE);
//        else vh.itemView.setVisibility(View.GONE);


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
                    String introId=items.get(position).intro;
                    String indutyId=items.get(position).induty;
                    String addr1Id=items.get(position).addr1;
                    String addr2Id=items.get(position).addr2;
                    String resveClId=items.get(position).resveCl;
                    String createdtimeId=items.get(position).createdtime;
                    String modifiedtimeId=items.get(position).modifiedtime;
                    String telId=items.get(position).tel;
                    String homepageId=items.get(position).homepage;
                    String resveUrlId=items.get(position).resveUrl;

                    Intent intent=new Intent(context,CampingApi_DetailActivity.class);
                    intent.putExtra("nameId",nameId);
                    intent.putExtra("lineintroId",lineintroId);
                    intent.putExtra("campingimgId",campingimgId);
                    intent.putExtra("introId",introId);
                    intent.putExtra("indutyId",indutyId);
                    intent.putExtra("addr1Id",addr1Id);
                    intent.putExtra("addr2Id",addr2Id);
                    intent.putExtra("resveClId",resveClId);
                    intent.putExtra("createdtimeId",createdtimeId);
                    intent.putExtra("modifiedtimeId",modifiedtimeId);
                    intent.putExtra("telId",telId);
                    intent.putExtra("homepageId",homepageId);
                    intent.putExtra("resveUrlId",resveUrlId);


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
